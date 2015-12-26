package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.faces.model.ListDataModel;
import javax.faces.validator.ValidatorException;
import javax.faces.application.FacesMessage;

import processamentoString.PreparaString;
import ModeloRepositorio.TbCategoriaRepository;
import ModeloRepositorio.TbEnderecoRepository;
import ModeloRepositorio.TbImagemRepository;
import ModeloRepositorio.TbPessoaFisicaRepository;
import ModeloRepositorio.TbOfertaRepository;
import ModeloRepositorio.TbTipoOfertaRepository;
import diversas.Categoria;
import diversas.Entity;
import diversas.Sessao;
import diversas.TipoOferta;
import modelo.TbCategoria;
import modelo.TbEndereco;
import modelo.TbImagem;
import modelo.TbPessoaFisica;
import modelo.TbOferta;
import modelo.TbTipoOferta;

@SessionScoped
@ManagedBean
public class TbOfertaBean {
	private TbOferta Oferta = new TbOferta();
	private TbImagem Imagem = new TbImagem();
	private TbEndereco Endereco = new TbEndereco();
	private DataModel lista;
	private int CodigoCat = 0;
	private int CodigoTipoOferta = 0;
	private Part file;
	private TbOferta OfertaTemp;
	private String FiltroAnuncios;
	private Boolean EnderecoOferta;

	public Boolean getEnderecoOferta() {
		return EnderecoOferta;
	}

	public void setEnderecoOferta(Boolean enderecoOferta) {
		EnderecoOferta = enderecoOferta;
	}

	public int getCodigoTipoOferta() {
		return CodigoTipoOferta;
	}

	public void setCodigoTipoOferta(int codigoTipoOferta) {
		CodigoTipoOferta = codigoTipoOferta;
	}

	public String getFiltroAnuncios() {
		return FiltroAnuncios;
	}

	public void setFiltroAnuncios(String filtroAnuncios) {
		FiltroAnuncios = filtroAnuncios;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public TbOferta getOferta() {
		return Oferta;
	}

	public void setOferta(TbOferta Oferta) {
		Oferta = Oferta;
	}

	public int getCodigoCat() {
		return CodigoCat;
	}

	public void setCodigoCat(int codigoCat) {
		CodigoCat = codigoCat;
	}

	public TbImagem getImagem() {
		return Imagem;
	}

	public void setImagem(TbImagem imagem) {
		Imagem = imagem;
	}

	public String detalharOferta() {
		Imagem = new TbImagem();
		Imagem = (TbImagem) (lista.getRowData());
		return "anuncio";
	}

	public DataModel<TbImagem> getimagens() {
		HttpSession ses = Sessao.RetornaSessao();
		EntityManager manager = Entity.RetornaEntityManager();
		TbImagemRepository repository = new TbImagemRepository(manager);
		List<TbImagem> AnuncioList;
		AnuncioList = repository.BuscaimagensPorIdOferta(Imagem.getTbOferta()
				.getPkCodOferta());
		lista = new ListDataModel(AnuncioList);
		return lista;
	}

	public String enviarEmail() {
		return "enviaemail";
	}

	public void limparObjeto() {
		this.Oferta = new TbOferta();
	}

	// Insere anúncio
	public String adicionaOferta() throws IOException {
		int max = 0;
		PreparaString manipulaString = new PreparaString(
				Oferta.getNmeDescricao());
		HttpSession ses = Sessao.RetornaSessao();
		EntityManager manager = Entity.RetornaEntityManager();
		TbOfertaRepository repository = new TbOfertaRepository(manager);
		TbCategoriaRepository repositoryCategoria = new TbCategoriaRepository(
				manager);
		TbTipoOfertaRepository repositoryTipoOferta = new TbTipoOfertaRepository(
				manager);
		TbPessoaFisicaRepository repositoryPessoa = new TbPessoaFisicaRepository(
				manager);
		Oferta.setTbCategoria(repositoryCategoria.BuscaCatPorId(CodigoCat));
		Oferta.setTbTipoOferta(repositoryTipoOferta
				.BuscaTipoPorId(CodigoTipoOferta));
		Oferta.setTbPessoaFisica(repositoryPessoa
				.BuscaPessoaPorId((Integer) ses.getAttribute("idusuario")));
		Oferta.setNmeDescricaoMatch(manipulaString.Descricao);
		repository.adiciona(this.Oferta);
		max = repository.RetornaMaxPorCliente(Oferta.getTbPessoaFisica()
				.getPkCodPessoaFisica());
		ses.setAttribute("idOfertaUser", max);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Anúncio Cadastrado com Sucesso!",
						"Anúncio Cadastrado com Sucesso!"));
		CodigoCat = 0;
		CodigoTipoOferta = 0;
		if (EnderecoOferta) {
			EnderecoOferta = false;
			this.Oferta = new TbOferta();
			this.Imagem = new TbImagem();
			return "cadastrarendereco";
		} else {
			TbEnderecoRepository repositoryEndereco = new TbEnderecoRepository(
					manager);
			this.Endereco.setTbUf(this.Oferta.getTbPessoaFisica().getTbUf());
			this.Endereco.setTbOferta(this.Oferta);
			this.Endereco.setNmeBairro(this.Oferta.getTbPessoaFisica()
					.getNmeBairro());
			this.Endereco
					.setNmeCep(this.Oferta.getTbPessoaFisica().getNmeCep());
			this.Endereco.setNmeCidade(this.Oferta.getTbPessoaFisica()
					.getNmeCidade());
			this.Endereco.setNmeComplemento(this.Oferta.getTbPessoaFisica()
					.getNmeComplemento());
			this.Endereco.setNmeEndereco(this.Oferta.getTbPessoaFisica()
					.getNmeEndereco());
			this.Endereco.setNmeNumero(this.Oferta.getTbPessoaFisica()
					.getNmeNumero());
			repositoryEndereco.adiciona(this.Endereco);
			this.Oferta = new TbOferta();
			this.Endereco = new TbEndereco();
			this.Imagem = new TbImagem();
			return "imagemupload";
		}
	}

	// Retorna todos os anúncios vinculados um anunciante
	public DataModel<TbOferta> getAnuncios() {
		HttpSession ses = Sessao.RetornaSessao();
		EntityManager manager = Entity.RetornaEntityManager();
		TbOfertaRepository repository = new TbOfertaRepository(manager);
		TbPessoaFisicaRepository repositoryPessoa = new TbPessoaFisicaRepository(
				manager);
		List<TbOferta> AnuncioList;
		AnuncioList = repository.buscaPorOferta(repositoryPessoa
				.BuscaPessoaPorId((Integer) ses.getAttribute("idusuario")));
		lista = new ListDataModel(AnuncioList);
		return lista;
	}

	public DataModel<TbOferta> getOfertasVenda() {
		HttpSession ses = Sessao.RetornaSessao();
		EntityManager manager = Entity.RetornaEntityManager();
		TbOfertaRepository repository = new TbOfertaRepository(manager);
		TbPessoaFisicaRepository repositoryPessoa = new TbPessoaFisicaRepository(
				manager);
		TbTipoOfertaRepository repositoryTipoOferta = new TbTipoOfertaRepository(
				manager);
		TbTipoOferta tipoOferta = new TbTipoOferta();
		tipoOferta = repositoryTipoOferta.BuscaTipoPorId(1);
		List<TbOferta> AnuncioList;
		AnuncioList = repository.buscaPorOfertaporTipo(repositoryPessoa
				.BuscaPessoaPorId((Integer) ses.getAttribute("idusuario")),
				tipoOferta);
		lista = new ListDataModel(AnuncioList);
		return lista;
	}

	public DataModel<TbOferta> getOfertasAluguel() {
		HttpSession ses = Sessao.RetornaSessao();
		EntityManager manager = Entity.RetornaEntityManager();
		TbOfertaRepository repository = new TbOfertaRepository(manager);
		TbPessoaFisicaRepository repositoryPessoa = new TbPessoaFisicaRepository(
				manager);
		TbTipoOfertaRepository repositoryTipoOferta = new TbTipoOfertaRepository(
				manager);
		TbTipoOferta tipoOferta = new TbTipoOferta();
		tipoOferta = repositoryTipoOferta.BuscaTipoPorId(2);
		List<TbOferta> AnuncioList;
		AnuncioList = repository.buscaPorOfertaporTipo(repositoryPessoa
				.BuscaPessoaPorId((Integer) ses.getAttribute("idusuario")),
				tipoOferta);
		lista = new ListDataModel(AnuncioList);
		return lista;
	}

	public DataModel<TbOferta> getOfertasDoacao() {
		HttpSession ses = Sessao.RetornaSessao();
		EntityManager manager = Entity.RetornaEntityManager();
		TbOfertaRepository repository = new TbOfertaRepository(manager);
		TbPessoaFisicaRepository repositoryPessoa = new TbPessoaFisicaRepository(
				manager);
		TbTipoOfertaRepository repositoryTipoOferta = new TbTipoOfertaRepository(
				manager);
		TbTipoOferta tipoOferta = new TbTipoOferta();
		tipoOferta = repositoryTipoOferta.BuscaTipoPorId(3);
		List<TbOferta> AnuncioList;
		AnuncioList = repository.buscaPorOfertaporTipo(repositoryPessoa
				.BuscaPessoaPorId((Integer) ses.getAttribute("idusuario")),
				tipoOferta);
		lista = new ListDataModel(AnuncioList);
		return lista;
	}

	// Retorna todos os anúncios
	public DataModel<TbOferta> getAnunciosImagem() {
		EntityManager manager = Entity.RetornaEntityManager();
		TbOfertaRepository repository = new TbOfertaRepository(manager);
		List<TbImagem> AnuncioList = null;
		if (FiltroAnuncios == null) {
			AnuncioList = repository.BuscaTodosOfertaImagem();
		} else {
			AnuncioList = repository.buscaPorString(FiltroAnuncios);
			setFiltroAnuncios(null);
		}
		lista = new ListDataModel(AnuncioList);
		return lista;
	}

	// Retorna categorias
	public DataModel<?> getCategorias() {
		Categoria lCategoria = new Categoria();
		return lCategoria.RetornaCategorias();
	}

	// Retorna tipos de oferta
	public DataModel<?> getTiposOfertas() {
		TipoOferta lTipoOferta = new TipoOferta();
		return lTipoOferta.RetornaTipoOfertas();
	}
	
	public String buscarDemanda(){
		HttpSession ses = Sessao.RetornaSessao();
		OfertaTemp = (TbOferta) lista.getRowData();
		ses.setAttribute("idOfertaMatch", OfertaTemp.getPkCodOferta());
		return "matchporoferta";
	}

	// Prepara exclusão, carrega objeto selecinado pelo usuário
	public void carregarOfertaExcluir() {
		OfertaTemp = (TbOferta) (lista.getRowData());
	}

	// Exclui anúncio
	public String excluirOferta() {
		EntityManager manager = Entity.RetornaEntityManager();
		TbOfertaRepository repository = new TbOfertaRepository(manager);
		repository.deletar(OfertaTemp);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso",
						"Anúncio Excluído!"));
		return "cadastrooferta";
	}

	// Carrega anúncio para edição
	public String carregarOferta() {
		TbOferta OfertaTemp = (TbOferta) (lista.getRowData());
		this.Oferta = OfertaTemp;
		CodigoCat = this.Oferta.getTbCategoria().getPkCodCategoria();
		CodigoTipoOferta = this.Oferta.getTbTipoOferta().getPkCodTipoOferta();
		EntityManager manager = Entity.RetornaEntityManager();
		TbImagemRepository repository = new TbImagemRepository(manager);
		this.Imagem = repository.BuscaOfertaPorIdOferta(this.Oferta
				.getPkCodOferta());
		return "editaroferta";
	}

	// Edita anúncio
	public String editarOferta() {
		HttpSession ses = Sessao.RetornaSessao();
		EntityManager manager = Entity.RetornaEntityManager();
		TbOfertaRepository repositoryOferta = new TbOfertaRepository(manager);
		TbCategoriaRepository repositoryCategoria = new TbCategoriaRepository(
				manager);
		this.Oferta
				.setTbCategoria(repositoryCategoria.BuscaCatPorId(CodigoCat));
		repositoryOferta.atualizar(this.Oferta);
		ses.setAttribute("idOfertaUser", this.Oferta.getPkCodOferta());
		this.Oferta = new TbOferta();
		CodigoCat = 0;
		CodigoTipoOferta = 0;
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Dados do anúncio alterados com sucesso!",
						"Dados do anúncio alterados com sucesso!"));
		if (EnderecoOferta) {
			EnderecoOferta = false;
			return "editarendereco";
		} else {
			return "imagemupload";
		}
	}

	public String cancelarOferta() {
		this.Oferta = new TbOferta();
		this.CodigoCat = 0;
		this.CodigoTipoOferta = 0;
		return "index";
	}

}
