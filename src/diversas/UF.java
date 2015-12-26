package diversas;

import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;

import modelo.TbCategoria;
import modelo.TbUf;
import ModeloRepositorio.TbCategoriaRepository;
import ModeloRepositorio.TbUfRepository;

public class UF {
private DataModel<TbUf> lista;
	
	//Retorna todas as categorias
	public DataModel<TbUf> RetornaUFs(){
		EntityManager manager = Entity.RetornaEntityManager();
		TbUfRepository repository = new TbUfRepository(manager);
		List<TbUf> UFList;
		UFList = repository.buscaTodos();
		lista = new ListDataModel<TbUf>(UFList);
		return lista;
	}

}
