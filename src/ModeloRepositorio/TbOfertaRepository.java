package ModeloRepositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.TbImagem;
import modelo.TbPessoaFisica;
import modelo.TbOferta;
import modelo.TbTipoOferta;
import ModeloRepositorio.TbTipoOfertaRepository;

public class TbOfertaRepository {
	private EntityManager manager;

	public TbOfertaRepository(EntityManager manager) {
		this.manager = manager;
	}

	public void adiciona(TbOferta Oferta) {
		this.manager.persist(Oferta);
	}

	public void atualizar(TbOferta Oferta) {
		this.manager.merge(Oferta);
	}

	// Retorna maior id
	public int RetornaMax() {
		int max = 0;
		Query query = this.manager
				.createQuery("select max(ev.pkCodOferta) as pkCodOferta from TbOferta as ev");
		List<Integer> List = query.getResultList();
		max = List.get(0);
		return max;
	}

	public int RetornaMaxPorCliente(int pCodigoUsuario) {
		int max = 0;
		Query query = this.manager
				.createQuery("select max(ev.pkCodOferta) as pkCodOferta from TbOferta as ev "
						+ "where ev.tbPessoaFisica.pkCodPessoaFisica = :codigo");
		query.setParameter("codigo", pCodigoUsuario);
		List<Integer> List = query.getResultList();
		max = List.get(0);
		return max;
	}

	// Busca anúncio por id
	public TbOferta BuscaOfertaPorId(int Codigo) {
		return this.manager.find(TbOferta.class, Codigo);
	}

	// Busca todos os anúncios
	public List<TbImagem> BuscaTodosOfertaImagem() {
		String hql = "select d from TbOferta p join p.tbImagems as d group by p.pkCodOferta";
		Query query = this.manager.createQuery(hql);
		List<TbImagem> OfertaList = query.getResultList();
		return OfertaList;
	}

	// Deleta anúncio
	public void deletar(TbOferta Oferta) {
		this.manager.remove(this.manager.find(TbOferta.class,
				Oferta.getPkCodOferta()));
	}

	// Busca todos os anúncio
	public List<TbOferta> buscaTodos() {
		Query query = this.manager.createQuery("select x from TbOferta x");
		return query.getResultList();
	}

	// Busca anúncio por pesquisa
	public List<TbImagem> buscaPorString(String pValor) {
		String hql = "select d from TbOferta p join p.tbImagems as d"
				+ " where p.tbCategoria.nmeNome like '%" + pValor + "%'";
		Query query = this.manager.createQuery(hql);
		List<TbImagem> OfertaList = query.getResultList();
		if (OfertaList.isEmpty()) {
			hql = "select d from TbOferta p join p.tbImagems as d"
					+ " where p.nmeDescricao like '%" + pValor + "%'";
			query = this.manager.createQuery(hql);
			OfertaList = query.getResultList();

		}
		return OfertaList;
	}

	// Busca anúncio por anunciante
	public List<TbOferta> buscaPorOferta(TbPessoaFisica PessoaFisica) {
		Query query = this.manager
				.createQuery("from TbOferta where tbPessoaFisica = :PessoaFisica");
		query.setParameter("PessoaFisica", PessoaFisica);
		return query.getResultList();
	}

	// Busca ofertas por anunciante
	public List<TbOferta> buscaPorOfertaporTipo(TbPessoaFisica PessoaFisica, TbTipoOferta TipoOferta) {
		TbTipoOferta lTipoOferta = new TbTipoOferta();
		Query query = this.manager
				.createQuery("from TbOferta where tbPessoaFisica = :PessoaFisica"
						+ " and tbTipoOferta = :TipoOferta");
		query.setParameter("PessoaFisica", PessoaFisica);
		query.setParameter("TipoOferta", TipoOferta);
		return query.getResultList();
	}

}
