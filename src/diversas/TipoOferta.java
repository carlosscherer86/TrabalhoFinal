package diversas;

import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;

import ModeloRepositorio.TbTipoOfertaRepository;
import modelo.TbTipoOferta;

public class TipoOferta {
private DataModel<TbTipoOferta> lista;
	
	//Retorna todas os tipos de oferta
	public DataModel<TbTipoOferta> RetornaTipoOfertas(){
		EntityManager manager = Entity.RetornaEntityManager();
		TbTipoOfertaRepository repository = new TbTipoOfertaRepository(manager);
		List<TbTipoOferta> TipoOfertaList;
		TipoOfertaList = repository.buscaTodos();
		lista = new ListDataModel<TbTipoOferta>(TipoOfertaList);
		return lista;
	}

}
