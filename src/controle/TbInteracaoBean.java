package controle;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import diversas.Entity;
import diversas.Sessao;
import ModeloRepositorio.TbInteracaoRepository;
import ModeloRepositorio.TbPessoaFisicaRepository;
import api.Email;
import modelo.TbImagem;
import modelo.TbInteracao;
import modelo.TbMatch;
import modelo.TbPessoaFisica;
import modelo.TbOferta;

@SessionScoped
@ManagedBean
public class TbInteracaoBean {
	private TbInteracao Interacao = new TbInteracao();
	private TbMatch Match = new TbMatch();

	//Verifica se usuário está logado e carrega página de envio de email.
	public String interacaoUsurioDemanda(TbMatch match) {
		HttpSession ses = Sessao.RetornaSessao();
		String usurio = (String) ses.getAttribute("usuario");
		if (usurio == null) {
			FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_INFO,
									"Para ter acesso a funcionalidade de envio de email é necessário realizar o Login!",
									""));

			return "login";
		} else {
			Match = new TbMatch();
			Match = match;
			return "enviaemaildemanda";
		}

	}
	
	public String interacaoUsurioOferta(TbMatch match) {
		HttpSession ses = Sessao.RetornaSessao();
		String usurio = (String) ses.getAttribute("usuario");
		if (usurio == null) {
			FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_INFO,
									"Para ter acesso a funcionalidade de envio de email é necessário realizar o Login!",
									""));

			return "login";
		} else {
			Match = new TbMatch();
			Match = match;
			return "enviaemailoferta";
		}

	}
	
	//Envia email para anunciante da demanda.
	public String enviaremailDemanda() {
		EntityManager manager = Entity.RetornaEntityManager();
		TbPessoaFisicaRepository repositoryPessoa = new TbPessoaFisicaRepository(
				manager);
		TbInteracaoRepository repository = new TbInteracaoRepository(manager);
		HttpSession ses = Sessao.RetornaSessao();
		Interacao.setDtaEnvio(new Date(System.currentTimeMillis()));
		Interacao.setTbOferta(Match.getTbOferta());
		Interacao.setTbDemanda(Match.getTbDemanda());
		Email email = new Email();
		email.enviarEmailDemanda(Interacao);
		repository.adiciona(Interacao);
		Interacao = new TbInteracao();
		return "matchdemanda";
	}
	
	//Envia email para anunciante.
		public String enviaremailOferta() {
			EntityManager manager = Entity.RetornaEntityManager();
			TbPessoaFisicaRepository repositoryPessoa = new TbPessoaFisicaRepository(
					manager);
			TbInteracaoRepository repository = new TbInteracaoRepository(manager);
			HttpSession ses = Sessao.RetornaSessao();
			Interacao.setDtaEnvio(new Date(System.currentTimeMillis()));
			Interacao.setTbOferta(Match.getTbOferta());
			Interacao.setTbDemanda(Match.getTbDemanda());
			Email email = new Email();
			email.enviarEmailOferta(Interacao);
			repository.adiciona(Interacao);
			Interacao = new TbInteracao();
			return "index";
		}

	public TbInteracao getInteracao() {
		return Interacao;
	}

	public void setInteracao(TbInteracao interacao) {
		Interacao = interacao;
	}

}
