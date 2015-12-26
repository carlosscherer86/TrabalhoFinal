package ModeloRepositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.TbDemanda;
import modelo.TbImagem;
import modelo.TbOferta;
import modelo.TbPessoaFisica;

public class TbDemandaRepository {
	private EntityManager manager;

	public TbDemandaRepository(EntityManager manager) {
		this.manager = manager;
	}

	public void adiciona(TbDemanda Demanda) {
		this.manager.persist(Demanda);
	}

	public void atualizar(TbDemanda Demanda) {
		this.manager.merge(Demanda);
	}


	//Busca an�ncio por id
	public TbDemanda BuscaDemandaPorId(int Codigo) {
		return this.manager.find(TbDemanda.class, Codigo);
	}

	//Deleta an�ncio
	public void deletar(TbDemanda Demanda) {
		this.manager.remove(this.manager.find(TbDemanda.class, Demanda.getPkCodDemanda()));
	}

	//Busca todos os an�ncio
	public List<TbDemanda> buscaTodos() {
		Query query = this.manager.createQuery("select x from TbDemanda x");
		return query.getResultList();
	}

	//Busca an�ncio por anunciante
	public List<TbDemanda> buscaPorDemanda(TbPessoaFisica PessoaFisica) {
		Query query = this.manager
				.createQuery("from TbDemanda where tbPessoaFisica = :PessoaFisica");
		query.setParameter("PessoaFisica", PessoaFisica);
		return query.getResultList();
	}

}

