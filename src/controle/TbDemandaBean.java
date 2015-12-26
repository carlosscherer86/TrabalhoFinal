package controle;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import processamentoString.PreparaString;
import modelo.TbDemanda;
import modelo.TbOferta;
import ModeloRepositorio.TbCategoriaRepository;
import ModeloRepositorio.TbDemandaRepository;
import ModeloRepositorio.TbPessoaFisicaRepository;
import diversas.Categoria;
import diversas.Entity;
import diversas.Sessao;

@SessionScoped
@ManagedBean
public class TbDemandaBean {
	private TbDemanda Demanda = new TbDemanda();
	private DataModel lista;
	private int CodigoCat;
	private TbDemanda DemandaTemp;


	public TbDemanda getDemanda() {
		return Demanda;
	}

	public void setDemanda(TbDemanda Demanda) {
		Demanda = Demanda;
	}

	public int getCodigoCat() {
		return CodigoCat;
	}

	public void setCodigoCat(int codigoCat) {
		CodigoCat = codigoCat;
	}

	
	public void limparObjeto(){
		this.Demanda = new TbDemanda();
	}

	//Insere anúncio
	public String adicionaDemanda() throws IOException {
		PreparaString manipulaString = new PreparaString(this.Demanda.getNmeDescricao());
		HttpSession ses = Sessao.RetornaSessao();
		EntityManager manager = Entity.RetornaEntityManager();
		TbDemandaRepository repository = new TbDemandaRepository(manager);
		TbCategoriaRepository repositoryCategoria = new TbCategoriaRepository(manager);
		TbPessoaFisicaRepository repositoryPessoa = new TbPessoaFisicaRepository(manager);
		Demanda.setTbCategoria(repositoryCategoria.BuscaCatPorId(CodigoCat));
		Demanda.setTbPessoaFisica(repositoryPessoa.BuscaPessoaPorId((Integer) ses.getAttribute("idusuario")));
		Demanda.setNmeDescricaoMatch(manipulaString.Descricao);
		
		repository.adiciona(this.Demanda);
		this.Demanda = new TbDemanda();
		CodigoCat = 0;
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Demanda Cadastrada com Sucesso!",
						"Demanda Cadastrada com Sucesso!"));
		
		return "paginainicial";
	}

	//Retorna todos os anúncios vinculados um anunciante
	public DataModel<TbDemanda> getDemandas() {
		HttpSession ses = Sessao.RetornaSessao();
		EntityManager manager = Entity.RetornaEntityManager();
		TbDemandaRepository repository = new TbDemandaRepository(manager);
		TbPessoaFisicaRepository repositoryPessoa = new TbPessoaFisicaRepository(manager);
		List<TbDemanda> AnuncioList;
		AnuncioList = repository.buscaPorDemanda(repositoryPessoa.BuscaPessoaPorId((Integer) ses.getAttribute("idusuario")));
		lista = new ListDataModel(AnuncioList);
		return lista;
	}
	
	//Retorna categorias
	public DataModel<?> getCategorias() {
		Categoria lCategoria= new Categoria();
		return lCategoria.RetornaCategorias();
	}
	
	//Prepara exclusão, carrega objeto selecinado pelo usuário
	public void carregarDemandaExcluir() {
		DemandaTemp = (TbDemanda) (lista.getRowData());
	}
	
	//Exclui anúncio
	public String excluirDemanda() {
		EntityManager manager = Entity.RetornaEntityManager();
		TbDemandaRepository repository = new TbDemandaRepository(manager);
		repository.deletar(DemandaTemp);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso",
						"Demanda Excluído!"));
		return "listardemanda";
	}
	
	//Carrega anúncio para edição
	public String carregarDemanda() {
		TbDemanda DemandaTemp = (TbDemanda) (lista.getRowData());
		this.Demanda= DemandaTemp;
		CodigoCat= this.Demanda.getTbCategoria().getPkCodCategoria();
		EntityManager manager = Entity.RetornaEntityManager();
		return "editardemanda";
	}
	
	//Edita anúncio
	public String editarDemanda() {
		HttpSession ses = Sessao.RetornaSessao();
		EntityManager manager = Entity.RetornaEntityManager();
		TbDemandaRepository repositoryDemanda = new TbDemandaRepository(manager);
		TbCategoriaRepository repositoryCategoria = new TbCategoriaRepository(manager);
		this.Demanda.setTbCategoria(repositoryCategoria.BuscaCatPorId(CodigoCat));
		repositoryDemanda.atualizar(this.Demanda);
		ses.setAttribute("idDemandaUser", this.Demanda.getPkCodDemanda());
		this.Demanda = new TbDemanda();
		CodigoCat = 0;
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados da demanda alterados com sucesso!",
						"Dados da demanda alterados com sucesso!"));
		
		return "listardemanda";
	}
	
	public String cancelarDemanda() {
		this.Demanda = new TbDemanda();
		this.CodigoCat = 0;
		return "index";
	}
	
	public String buscarOfertas(){
		HttpSession ses = Sessao.RetornaSessao();
		DemandaTemp = (TbDemanda) lista.getRowData();
		ses.setAttribute("idDemandaMatch", DemandaTemp.getPkCodDemanda());
		return "matchpordemanda";
	}
	
}

