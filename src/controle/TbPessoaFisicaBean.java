package controle;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import ModeloRepositorio.TbPessoaFisicaRepository;
import ModeloRepositorio.TbUfRepository;
import diversas.Categoria;
import diversas.Entity;
import diversas.Sessao;
import diversas.UF;
import modelo.TbPessoaFisica;
import modelo.TbUf;

@ManagedBean
public class TbPessoaFisicaBean {
	private TbPessoaFisica PessoaFisica = new TbPessoaFisica();
	private String statusLogar;
	private String visivelAlerta = "hidden";
	private String emailInvalido;
	private int codUf;
	private List<TbPessoaFisica> PessoafisicaList;
	private TbPessoaFisica PessoaFisicaTemp = new TbPessoaFisica();

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

	public String getEmailInvalido() {
		return emailInvalido;
	}

	public void setEmailInvalido(String emailInvalido) {
		this.emailInvalido = emailInvalido;
	}

	// Retorna UFs
	public DataModel<?> getUFs() {
		UF lUFs = new UF();
		return lUFs.RetornaUFs();
	}

	// Verifica se usuário tem permissão de administrador
	public Boolean getAdmin() {
		HttpSession ses = Sessao.RetornaSessao();
		Byte admin = 0;
		admin = (Byte) ses.getAttribute("administrador");

		if (admin == null) {
			return false;
		} else {
			if (admin == 1) {
				return true;
			} else {
				return false;
			}
		}
	}

	// Verifica se usuário está logado
	public String getSessao() {
		HttpSession ses = Sessao.RetornaSessao();
		String usuario = "";
		usuario = (String) ses.getAttribute("usuario");
		return usuario;
	}

	// Verifica se email já não existe no banco de dados
	public void validaEmail(boolean ehEdicao) {
		HttpSession ses = Sessao.RetornaSessao();
		EntityManager manager = Entity.RetornaEntityManager();
		TbPessoaFisicaRepository repository = new TbPessoaFisicaRepository(
				manager);
		PessoaFisicaTemp = repository.validaEmail(PessoaFisica.getNmeEmail());

		if (PessoaFisicaTemp != null) {
			if (ehEdicao) {
				if (!PessoaFisicaTemp.getNmeEmail().equals(
						ses.getAttribute("emailUsuario"))) {
					this.emailInvalido = "Email já cadastrado!";
					PessoaFisica.setNmeEmail("");
				}
			} else {
				this.emailInvalido = "Email já cadastrado!";
				PessoaFisica.setNmeEmail("");
			}
		}
	}

	// Carrega dados do anunciante
	public void carregaCliente() {
		HttpSession ses = Sessao.RetornaSessao();
		EntityManager manager = Entity.RetornaEntityManager();
		TbPessoaFisicaRepository repository = new TbPessoaFisicaRepository(
				manager);
		this.PessoaFisica = repository.buscaCliente((Integer) ses
				.getAttribute("idusuario"));
		ses.setAttribute("emailUsuario", this.PessoaFisica.getNmeEmail());
		codUf = this.PessoaFisica.getTbUf().getPkCodUf();
	}

	// Realiza logoff
	public String logoff() {
		HttpSession ses = Sessao.RetornaSessao();
		ses.invalidate();
		return "login";
	}

	public String getStatusLogar() {
		return statusLogar;
	}

	public void setStatusLogar(String statusLogar) {
		this.statusLogar = statusLogar;
	}

	// Realiza login
	public String usuarioLogar() {
		EntityManager manager = Entity.RetornaEntityManager();
		TbPessoaFisicaRepository repository = new TbPessoaFisicaRepository(
				manager);
		PessoafisicaList = repository.loginUsuario(PessoaFisica.getNmeSenha(),
				PessoaFisica.getNmeEmail());

		if (PessoafisicaList.size() == 0) {
			statusLogar = "Email ou Senha Inválida!";
			visivelAlerta = "visible";
			return "login";
		} else {
			HttpSession sessao = Sessao.RetornaSessao();
			sessao.setAttribute("idusuario", PessoafisicaList.get(0)
					.getPkCodPessoaFisica());
			sessao.setAttribute("senhausuario", PessoafisicaList.get(0)
					.getNmeSenha());
			sessao.setAttribute("usuario", PessoafisicaList.get(0).getNmeNome());
			sessao.setAttribute("administrador", PessoafisicaList.get(0)
					.getStaAdmin());
			return "paginainicial";
		}

	}

	// Insere anunciante
	public String adicionaPessoaFisica() {
		EntityManager manager = Entity.RetornaEntityManager();
		TbPessoaFisicaRepository repository = new TbPessoaFisicaRepository(
				manager);
		TbUfRepository repositoryUf = new TbUfRepository(
				manager);
		this.PessoaFisica.setStaAdmin((byte) 0);
		this.PessoaFisica.setTbUf(repositoryUf.BuscaUfPorId(codUf));
		repository.adiciona(this.PessoaFisica);
		this.PessoaFisica = new TbPessoaFisica();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Anunciante Cadastrado com Sucesso!",
						"Anunciante Cadastrado com Sucesso!"));
		return "login";
	}

	// Edita anunciante
	public String editaPessoaFisica() {
		HttpSession ses = Sessao.RetornaSessao();
		EntityManager manager = Entity.RetornaEntityManager();
		TbPessoaFisicaRepository repository = new TbPessoaFisicaRepository(
				manager);
		TbUfRepository repositoryUf = new TbUfRepository(
				manager);
		this.PessoaFisica.setPkCodPessoaFisica((Integer) ses
				.getAttribute("idusuario"));
		this.PessoaFisica
				.setNmeSenha((String) ses.getAttribute("senhausuario"));
		this.PessoaFisica.setStaAdmin((byte) ses.getAttribute("administrador"));
		this.PessoaFisica.setTbUf(repositoryUf.BuscaUfPorId(codUf));
		repository.atualizar(this.PessoaFisica);
		this.PessoaFisica = new TbPessoaFisica();
		ses.setAttribute("emailUsuario", "");
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Dados cadastrais alterados com sucesso!",
						"Dados cadastrais alterados com sucesso!"));

		return "index";
	}

	public TbPessoaFisica getPessoaFisica() {
		return PessoaFisica;
	}

	// Direciona para a página de cadastro de cliente
	public String CadastroCliente() {
		return "cadastrarcliente";
	}

	public void setPessoaFisica(TbPessoaFisica pessoaFisica) {
		PessoaFisica = pessoaFisica;
	}

}
