package ModeloRepositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.TbImagem;
import modelo.TbSinonimo;

public class TbSinonimoRepository {
	private EntityManager manager;
	
	public TbSinonimoRepository(EntityManager manager) {
		this.manager = manager;
	}

	public void adiciona(TbSinonimo Sinonimo) {
		this.manager.persist(Sinonimo);
	}
	
	public List<TbSinonimo> buscaPalavra(String pPalavra){
		String hql = "select s from TbSinonimo s"
				+ " where s.nmeSinonimo like '" + pPalavra + "'";
		Query query = this.manager.createQuery(hql);
		List<TbSinonimo> SinonimoList = query.getResultList();
		return SinonimoList;
	}
	
	public List<TbSinonimo> buscaSinonimo (int pGrupo){
		String hql = "select s from TbSinonimo s"
				+ " where s.numGrupo = " + pGrupo +" and s.numSequencia = 1";
		Query query = this.manager.createQuery(hql);
		List<TbSinonimo> SinonimoList = query.getResultList();
		return SinonimoList;
	}
	
	public int buscaGrupoId(){
		int max = 0;
		Query query = this.manager
				.createQuery("select max(ev.numGrupo) as numGrupo from TbSinonimo as ev");
		List<Integer> List = query.getResultList();
		max = List.get(0);
		return max;
	}

}
