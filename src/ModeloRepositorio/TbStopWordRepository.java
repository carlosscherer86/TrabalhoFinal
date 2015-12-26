package ModeloRepositorio;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.TbStopWord;

public class TbStopWordRepository {
	private EntityManager manager;

	public TbStopWordRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	@SuppressWarnings("unchecked")
	public String BuscaStopWord(String pWord){
		String hql = "select s from TbStopWord s"
				+ " where s.nmeStopWord = '" + pWord + "'";
		Query query = this.manager.createQuery(hql);
		List<TbStopWord> List = query.getResultList();
		if (List.isEmpty()){
			return "";
		}else{
			return List.get(0).getNmeStopWord();
		}
	}

}
