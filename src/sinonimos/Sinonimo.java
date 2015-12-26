package sinonimos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import modelo.TbSinonimo;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import diversas.Entity;
import ModeloRepositorio.TbSinonimoRepository;

@SuppressWarnings("deprecation")
public class Sinonimo {
	private final DefaultHttpClient client = new DefaultHttpClient();
	private static List<String> list;
	private String sinonimoPrincipal;
	private TbSinonimo sinonimo;
	private TbSinonimoRepository repository;
	private EntityManager manager;
	
	public Sinonimo(){
		manager = Entity.RetornaEntityManager();
		repository = new TbSinonimoRepository(manager);
	}
	
	/** * Abre página * @param url - Página a acessar * @throws IOException */
	public void openPage(final String url) throws IOException {
		final HttpGet get = new HttpGet(url);
		final HttpResponse response = client.execute(get);
		saveHTLM(response);
	}

	/** * Encerra conexão */
	public void close() {
		client.getConnectionManager().shutdown();
	}
	
	public boolean VerificaSinonimo(String pPalavra){
		List<TbSinonimo> list = repository.buscaPalavra(pPalavra);
		if (list.isEmpty()){
			return false;
		}else{
			List<TbSinonimo> listSinonimo = repository.buscaSinonimo(list.get(0).getNumGrupo());
			sinonimoPrincipal = listSinonimo.get(0).getNmeSinonimo();
			return true;
		}
	}

	/** * Salva a página * @param response * @throws IOException */
	private void saveHTLM(final HttpResponse response) throws IOException {
		final BufferedReader rd = new BufferedReader(new InputStreamReader(
				response.getEntity().getContent()));
		String line;
		String html = "";
		while ((line = rd.readLine()) != null) {
			html= html +line + (char)13;
		}
		Document doc = Jsoup.parse(html);
		
		Element link = doc.getElementsByClass("sinonimos").first();
		String palavra;
		
		for (Element l : link.getElementsByClass("sinonimo")) {
			palavra= l.text();
			list.add(palavra);
		}
	}
	
	public void SalvaSinonimos(){
		int lCount = 0;
		int lGrupo = 0;
		EntityManager manager = Entity.RetornaEntityManager();
		TbSinonimoRepository repository = new TbSinonimoRepository(manager);
		lGrupo = repository.buscaGrupoId() + 1;
		for (String l : list) {
			lCount = lCount + 1;
			sinonimo = new TbSinonimo();
			sinonimo.setNmeSinonimo(l);
			sinonimo.setNumGrupo(lGrupo);
			sinonimo.setNumSequencia(lCount);
			repository.adiciona(this.sinonimo);
		}
	}
	

	public static String BuscaSinonimo(String pPalavra) throws IOException {
		boolean ExisteBase;
		Sinonimo navegador = new Sinonimo();
		list = new ArrayList<String>();
		list.add(pPalavra);
		ExisteBase= navegador.VerificaSinonimo(pPalavra);
		if (!ExisteBase){
			navegador.openPage("http://www.sinonimos.com.br/"+pPalavra+"/");
			navegador.close();
			navegador.SalvaSinonimos();
			navegador.sinonimoPrincipal= pPalavra;
		}
		return navegador.sinonimoPrincipal;

	}
}
