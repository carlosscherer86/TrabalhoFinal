package ModeloRepositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.TbCategoria;
import modelo.TbUf;

public class TbUfRepository {
	private EntityManager manager;

	public TbUfRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	//Busca UF por id
	public TbUf BuscaUfPorId(int Codigo){
		return this.manager.find(TbUf.class, Codigo);
	}

	//Insere UF
	public void adiciona(TbUf Uf) {
		this.manager.persist(Uf);
	}
	
	//Edita UF
	public void atualizar(TbUf Uf) {
		this.manager.merge(Uf);
	}
	
	//Deleta UF
	public void deletar(TbUf Uf) {
		this.manager.remove(this.manager.find(TbUf.class, Uf.getPkCodUf()));
	}

	//Busca todas as UF
	public List<TbUf> buscaTodos() {
		Query query = this.manager.createQuery("select x from TbUf x");
		return query.getResultList();
	}

}
