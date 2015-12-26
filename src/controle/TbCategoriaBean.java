package controle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import ModeloRepositorio.TbCategoriaRepository;
import modelo.TbCategoria;
import diversas.*;

//Controle da classe TBCategoria
@SessionScoped
@ManagedBean
public class TbCategoriaBean {
	private TbCategoria Categoria = new TbCategoria();
	private TbCategoria CategoriaTemp;
	private DataModel lista;

	//Adiciona nova categoria utilizado o framework Hibernate
	public String adicionaCategoria() {
		EntityManager manager = Entity.RetornaEntityManager();
		TbCategoriaRepository repository = new TbCategoriaRepository(manager);
		repository.adiciona(this.Categoria);
		this.Categoria = new TbCategoria();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Categoria Inserida com sucesso!",
						"Categoria Inserida com sucesso!"));
		return "listarcategoria";
	}
	

	public TbCategoria getCategoria() {
		return Categoria;
	}

	public void setCategoria(TbCategoria categoria) {
		Categoria = categoria;
	}
	
	//retorna todas as categorias salvas no banco de dados
	public DataModel<TbCategoria> getCategorias() {
		Categoria lCategoria= new Categoria();
		lista = lCategoria.RetornaCategorias();
		return lista;
	}

	//Exclui uma categoria
	public String excluirCategoria() {
		EntityManager manager = Entity.RetornaEntityManager();
		TbCategoriaRepository repository = new TbCategoriaRepository(manager);
		repository.deletar(CategoriaTemp);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Categoria Excluída com sucesso!",
						"Categoria Excluída com sucesso!"));
		return "listarcategoria";
	}
	
	//Carrega o objeto selecionado pelo usuário no Grid para exclusão
	public void carregarCategoriaExcluir() {
		CategoriaTemp = (TbCategoria) (lista.getRowData());
	}

	//Carrega o objeto selecionado pelo usuário no Grid para edição e chama tela de edição
	public String carregarCategoria() {
		TbCategoria CategoriaTemp = (TbCategoria) (lista.getRowData());
		this.Categoria.setNmeDescricao(CategoriaTemp.getNmeDescricao());
		this.Categoria.setNmeNome(CategoriaTemp.getNmeNome());
		HttpSession sessao= Sessao.RetornaSessao();
		sessao.setAttribute("idcategoria", CategoriaTemp.getPkCodCategoria());
		return "editarcategoria";
	}
	
	public String cancelarCategoria() {
		this.Categoria = new TbCategoria();
		return "index";
	}
	
	//Salva as informações alteradas pelo usuário na edição da categoria
	public String editarCategoria() {
		EntityManager manager = Entity.RetornaEntityManager();
		TbCategoriaRepository repository = new TbCategoriaRepository(manager);
		HttpSession ses= Sessao.RetornaSessao();
		this.Categoria.setPkCodCategoria((Integer)ses.getAttribute("idcategoria"));
		repository.atualizar(this.Categoria);
		this.Categoria = new TbCategoria();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados alterados com sucesso!",
						"Dados alterados com sucesso!"));
		
		return "listarcategoria";
	}

}
