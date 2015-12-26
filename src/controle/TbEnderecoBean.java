package controle;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import modelo.TbEndereco;
import modelo.TbPessoaFisica;
import ModeloRepositorio.TbEnderecoRepository;
import ModeloRepositorio.TbOfertaRepository;
import ModeloRepositorio.TbPessoaFisicaRepository;
import ModeloRepositorio.TbUfRepository;
import diversas.Entity;
import diversas.Sessao;
import diversas.UF;

@ManagedBean
public class TbEnderecoBean {
	private TbEndereco Endereco = new TbEndereco();
	private String visivelAlerta = "hidden";
	private int codUf;
	private TbEndereco EnderecoTemp = new TbEndereco();

	public String getVisivelAlerta() {
		return visivelAlerta;
	}

	public void setVisivelAlerta(String visivelAlerta) {
		this.visivelAlerta = visivelAlerta;
	}

	public int getCodUf() {
		return codUf;
	}

	public void setCodUf(int codUf) {
		this.codUf = codUf;
	}

	// Retorna UFs
	public DataModel<?> getUFs() {
		UF lUFs = new UF();
		return lUFs.RetornaUFs();
	}

	// Insere anunciante
	public String adicionaEndereco() {
		EntityManager manager = Entity.RetornaEntityManager();
		HttpSession ses = Sessao.RetornaSessao();
		TbEnderecoRepository repository = new TbEnderecoRepository(manager);
		TbOfertaRepository repositoryOferta = new TbOfertaRepository(manager);
		TbUfRepository repositoryUf = new TbUfRepository(manager);
		this.Endereco.setTbUf(repositoryUf.BuscaUfPorId(codUf));
		this.Endereco.setTbOferta(repositoryOferta
				.BuscaOfertaPorId((Integer) ses.getAttribute("idOfertaUser")));
		repository.adiciona(this.Endereco);
		this.Endereco = new TbEndereco();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Endereço Cadastrado com Sucesso!",
						"Endereço Cadastrado com Sucesso!"));
		return "imagemupload";
	}

	// Carrega dados do anunciante
	public void carregaEndereco() {
		HttpSession ses = Sessao.RetornaSessao();
		EntityManager manager = Entity.RetornaEntityManager();
		TbEnderecoRepository repository = new TbEnderecoRepository(manager);
		this.Endereco = repository.buscaEndereco((Integer) ses
				.getAttribute("idOfertaUser"));
		codUf = this.Endereco.getTbUf().getPkCodUf();
	}

	// Edita anunciante
	public String editaEndereco() {
		HttpSession ses = Sessao.RetornaSessao();
		EntityManager manager = Entity.RetornaEntityManager();
		TbEnderecoRepository repository = new TbEnderecoRepository(
				manager);
		TbUfRepository repositoryUf = new TbUfRepository(
				manager);
		TbOfertaRepository repositoryOferta = new TbOfertaRepository(
				manager);
		this.EnderecoTemp = repository.buscaEndereco((Integer) ses.getAttribute("idOfertaUser"));
		this.Endereco.setPkCodEndereco(this.EnderecoTemp.getPkCodEndereco());
		this.Endereco.setTbUf(repositoryUf.BuscaUfPorId(codUf));
		this.Endereco.setTbOferta(repositoryOferta
				.BuscaOfertaPorId((Integer) ses
						.getAttribute("idOfertaUser")));
		repository.atualizar(this.Endereco);
		this.Endereco = new TbEndereco();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Endereço alterado com sucesso!",
						""));

		return "imagemupload";
	}

	public TbEndereco getEndereco() {
		return Endereco;
	}

	public void setPessoaFisica(TbEndereco endereco) {
		Endereco = endereco;
	}

}
