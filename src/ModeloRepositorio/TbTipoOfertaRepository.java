package ModeloRepositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import modelo.TbTipoOferta;

public class TbTipoOfertaRepository {
	private EntityManager manager;

	public TbTipoOfertaRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	//Busca tipo oferta por id
	public TbTipoOferta BuscaTipoPorId(int Codigo){
		return this.manager.find(TbTipoOferta.class, Codigo);
	}

	//Insere Tipo Oferta
	public void adiciona(TbTipoOferta TipoOferta) {
		this.manager.persist(TipoOferta);
	}
	
	//Edita tipo oferta
	public void atualizar(TbTipoOferta TipoOferta) {
		this.manager.merge(TipoOferta);
	}
	
	//Deleta tipo oferta
	public void deletar(TbTipoOferta TipoOferta) {
		this.manager.remove(this.manager.find(TbTipoOferta.class, TipoOferta.getPkCodTipoOferta()));
	}

	//Busca todas as categorias
	public List<TbTipoOferta> buscaTodos() {
		Query query = this.manager.createQuery("select x from TbTipoOferta x");
		return query.getResultList();
	}

}
