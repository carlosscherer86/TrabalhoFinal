package ModeloRepositorio;

import javax.persistence.EntityManager;
import modelo.TbInteracao;

public class TbInteracaoRepository {
	private EntityManager manager;

	public TbInteracaoRepository(EntityManager manager) {
		this.manager = manager;
	}


	//Salva  interação
	public void adiciona(TbInteracao Interacao) {
		this.manager.persist(Interacao);
	}
	
}
