package filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;

import diversas.Sessao;

@WebFilter(servletNames = { "Faces Servlet" })
public class JPAFilter implements Filter {

	private EntityManagerFactory factory;
	

	// private HttpSession session;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//cria a conexão com o banco de dados via Hibernate
		this.factory = Persistence.createEntityManagerFactory("TrabalhoFinal");
	}

	@Override
	public void destroy() {
		this.factory.close();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		// CHEGADA
		//Anexa a conexão cria à requisição
		EntityManager manager = this.factory.createEntityManager();
		request.setAttribute("EntityManager", manager);
		manager.getTransaction().begin();
		// CHEGADA
		//validaRequisicao();
		// FACES SERVLET
		chain.doFilter(request, response);
		// FACES SERVLET
		// SAÍDA
		try {
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		// SAÍDA
	}
	
	private void validaRequisicao(){
		try{
		HttpSession ses = Sessao.RetornaSessao();
		if( ses.getAttribute("idusuario") != null){
			System.out.println("ok");
		}else{
			System.out.println("not ok");
		}
		}catch(Exception  ex){
			System.out.println("not ok");
		}
	}

}
