package ModeloRepositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.TbDemanda;
import modelo.TbMatch;
import modelo.TbOferta;

public class TbMatchRepository {
	private EntityManager manager;

	public TbMatchRepository(EntityManager manager) {
		this.manager = manager;
	}

	public void adiciona(TbMatch Match) {
		this.manager.persist(Match);
	}

	public void atualizar(TbMatch Match) {
		this.manager.merge(Match);
	}

	// Busca todos os Match
	public List<TbMatch> buscaTodos() {
		Query query = this.manager.createQuery("select x from TbMatch x");
		return query.getResultList();
	}

	// Busca todos os Match
	public List<TbMatch> buscaTodosPorOferta(TbOferta pOferta) {
		Query query = this.manager.createQuery("select x from TbMatch x"
				+ " where x.tbOferta = :Oferta "
				+ "order by x.numDistancia");
		query.setParameter("Oferta", pOferta);
		return query.getResultList();
	}
	
	// Busca todos os Match por demanda
		public List<TbMatch> buscaTodosPorDemanda(TbDemanda pDemanda) {
			Query query = this.manager.createQuery("select x from TbMatch x"
					+ " where x.tbDemanda = :Demanda "
					+ "order by x.numDistancia");
			query.setParameter("Demanda", pDemanda);
			return query.getResultList();
		}

	// Busca todos os Match Demanda por usuário
	public List<TbMatch> buscaMatchDemanda(int pCodPessoaFisica) {
		Query query = this.manager
				.createQuery("select x from TbMatch x where x.tbDemanda.tbPessoaFisica.pkCodPessoaFisica = :codPessoa");
		query.setParameter("codPessoa", pCodPessoaFisica);
		return query.getResultList();
	}

	public List<TbMatch> buscaMatchOferta(int pCodPessoaFisica,
			int pCodTipoOferta) {
		Query query = this.manager
				.createQuery("select x from TbMatch x "
						+ "where x.tbOferta.tbPessoaFisica.pkCodPessoaFisica = :codPessoa "
						+ "and x.tbOferta.tbTipoOferta.pkCodTipoOferta = :codTipoOferta");
		query.setParameter("codPessoa", pCodPessoaFisica);
		query.setParameter("codTipoOferta", pCodTipoOferta);
		return query.getResultList();
	}
	
	public boolean verificaMatch(int pCodOferta, int pCodDemanda){
		Query query = this.manager
				.createQuery("select x from TbMatch x "
						+ "where x.tbDemanda.pkCodDemanda = :codDemanda"
						+ " and x.tbOferta.pkCodOferta = :codOferta");
		query.setParameter("codDemanda", pCodDemanda);
		query.setParameter("codOferta", pCodOferta);
		List<TbMatch> list= query.getResultList();  
		return list.isEmpty();
	}
	

}
