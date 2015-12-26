package api;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import modelo.TbDemanda;
import modelo.TbInteracao;
import modelo.TbOferta;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.mail.HtmlEmail;

//API de envio de email.
public class Email {
	private TbInteracao interacao;

	// Método utilazado para comunicação entre as classes
	public void enviarEmailDemanda(TbInteracao pInteracao) {
		interacao = pInteracao;
		try {
			this.encaminharEmailDemanda();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Email Enviado!", "Email Enviado!"));
		} catch (EmailException e) {
			e.printStackTrace();
		}

	}

	public void enviarEmailOferta(TbInteracao pInteracao) {
		interacao = pInteracao;
		try {
			this.encaminharEmailOferta();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Email Enviado!", "Email Enviado!"));
		} catch (EmailException e) {
			e.printStackTrace();
		}

	}

	public void enviarEmail(TbOferta oferta, TbDemanda demanda) {
		try {
			this.notificaEmail(oferta, demanda);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void enviarEmailNovo(TbOferta oferta, TbDemanda demanda) {
		try {
			this.notificaEmailDemanda(oferta, demanda);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Método que realiza o envio do email
	private void encaminharEmailDemanda() throws EmailException {
		SimpleEmail email = new SimpleEmail();
		email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio
												// e-mail
		email.addTo(interacao.getTbDemanda().getTbPessoaFisica().getNmeEmail(),
				interacao.getTbDemanda().getTbPessoaFisica().getNmeNome()); // destinatário
		email.setFrom(
				interacao.getTbOferta().getTbPessoaFisica().getNmeEmail(),
				interacao.getTbOferta().getTbPessoaFisica().getNmeNome()); // remetente
		email.setSubject("Protótipo"); // assunto do e-mail
		email.setMsg(interacao.getNmeMensagem()); // conteudo do e-mail
		email.setAuthentication("prototipo1986@gmail.com", "graziela@1");  
        email.setSSL(true);
        email.setTLS(true);
		email.send(); // envia o e-mail

	}

	// Método que realiza o envio do email
	private void encaminharEmailOferta() throws EmailException {
		SimpleEmail email = new SimpleEmail();
		email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio
												// e-mail
		email.addTo(interacao.getTbOferta().getTbPessoaFisica().getNmeEmail(),
				interacao.getTbOferta().getTbPessoaFisica().getNmeNome()); // destinatário
		email.setFrom(interacao.getTbDemanda().getTbPessoaFisica()
				.getNmeEmail(), interacao.getTbDemanda().getTbPessoaFisica()
				.getNmeNome()); // remetente
		email.setSubject("Protótipo"); // assunto do e-mail
		email.setMsg(interacao.getNmeMensagem()); // conteudo do e-mail
		email.setAuthentication("prototipo1986@gmail.com", "graziela@1");  
        email.setSSL(true);
        email.setTLS(true);
		email.send(); // envia o e-mail

	}

	// Método que realiza o envio do email
	private void notificaEmail(TbOferta oferta, TbDemanda demanda)
			throws EmailException, MalformedURLException {
		HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.gmail.com");
		email.setSSL(true);
        email.setTLS(true);
		email.setAuthentication("prototipo1986@gmail.com", "graziela@1");
		// email.setCharset(emailEncoding);

		email.addTo(demanda.getTbPessoaFisica().getNmeEmail(), demanda
				.getTbPessoaFisica().getNmeNome());
		email.setFrom("sistemarecomenda@sistemarecomenda.com",
				"Sistema de Recomendação");
		email.setSubject("Sistema de Recomendação");

		String nome = "/opt/tomcat/webapps/TrabalhoFinal/imagem_produto/" + oferta.getTbImagems().get(0).getNmeImagem();
		System.out.println(nome);
		String cid2 = email.embed(new File(nome));

		email.setHtmlMsg("<html>"
				+ "<div class=\"media\">"
				+ "<img class=\"pull-left\" src=\"cid:"
				+ cid2
				+ "\" style=\"height: 130px; width: 160px;\"/>"
				+ "<div class=\"media-body fnt-smaller\">"
				+ "<a href=\"#\" target=\"_parent\"></a>"
				+ "<h4 class=\"media-heading\">"
				+ "<a target=\"_parent\">"
				+ oferta.getNmeDescricao()
				+ "<small class=\"pull-right\"></small> </a>"
				+ "</h4>"
				+ "<p class=\"hidden-xs\">R$ "
				+ oferta.getVlrUnidade()
				+ "</p>"
				+ "<div class=\"col-sm-4 pull-right\">"
				+ "<a href=\"http://localhost/TrabalhoFinal/login.xhtml\">Acessar Sistema</a>"
				+ "</div>" + "</div>" + "</div>" + "</html>");

		email.setTextMsg("Your email client does not support HTML messages");

		email.send();
	}

	// Método que realiza o envio do email
	private void notificaEmailDemanda(TbOferta oferta, TbDemanda demanda)
			throws EmailException, MalformedURLException {
		HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.gmail.com");
		email.setSSL(true);
        email.setTLS(true);
		email.setAuthentication("prototipo1986@gmail.com", "graziela@1");
		// email.setCharset(emailEncoding);

		email.addTo(oferta.getTbPessoaFisica().getNmeEmail(), oferta
				.getTbPessoaFisica().getNmeNome());
		email.setFrom("sistemarecomenda@sistemarecomenda.com",
				"Sistema de Recomendação");
		email.setSubject("Sistema de Recomendação");

		String nome = "/opt/tomcat/webapps/TrabalhoFinal/imagem_produto/" + oferta.getTbImagems().get(0).getNmeImagem();
		System.out.println(nome);
		String cid2 = email.embed(new File(nome));

		
		email.setHtmlMsg("<html>"
				+ "<div class=\"media\">"
				+ "<img class=\"pull-left\" src=\"cid:"
				+ cid2
				+ "\" style=\"height: 130px; width: 160px;\"/>"
				+ "<div class=\"media-body fnt-smaller\">"
				+ "<a href=\"#\" target=\"_parent\"></a>"
				+ "<h4 class=\"media-heading\">"
				+ "<a target=\"_parent\">Oferta: "
				+ oferta.getNmeDescricao()
				+ "<small class=\"pull-right\"></small> </a>"
				+ "</h4>"
				+ "<h4 class=\"media-heading\">"
				+ "<a target=\"_parent\">Demanda: "
				+ demanda.getNmeDescricao()
				+ "<small class=\"pull-right\"></small> </a>"
				+ "</h4>"
				+ "<p class=\"hidden-xs\">R$ "
				+ oferta.getVlrUnidade()
				+ "</p>"
				+ "<div class=\"col-sm-4 pull-right\">"
				+ "<a href=\"http://env-0904840.jelasticlw.com.br/TrabalhoFinal/login.xhtml\">Acessar Sistema</a>"
				+ "</div>" + "</div>" + "</div>" + "</html>");

		email.setTextMsg("Your email client does not support HTML messages");

		email.send();
	}

}
