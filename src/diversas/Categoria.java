package diversas;

import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import ModeloRepositorio.TbCategoriaRepository;
import modelo.TbCategoria;

public class Categoria {
	private DataModel<TbCategoria> lista;
	
	//Retorna todas as categorias
	public DataModel<TbCategoria> RetornaCategorias(){
		EntityManager manager = Entity.RetornaEntityManager();
		TbCategoriaRepository repository = new TbCategoriaRepository(manager);
		List<TbCategoria> CategoriaList;
		CategoriaList = repository.buscaTodos();
		lista = new ListDataModel<TbCategoria>(CategoriaList);
		return lista;
	}

}
