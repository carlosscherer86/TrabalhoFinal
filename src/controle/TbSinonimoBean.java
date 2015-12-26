package controle;

import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.annolab.tt4j.TokenHandler;
import org.annolab.tt4j.TreeTaggerException;
import org.annolab.tt4j.TreeTaggerWrapper;

import analiseSintatica.AnaliseSintatica;
import diversas.Levenshtein;
import radicalizacao.Radicalizacao;

import java.util.Arrays;

@SessionScoped
@ManagedBean
public class TbSinonimoBean {
	private String lista;

	public void adicionaPalavra() throws IOException, TreeTaggerException {
		//StringTokenizer n = new StringTokenizer("Dá uma olhada em expressões regulares. Aqui tem um tutorial bom, usando a api padrão:");
		//System.out.println(n.countTokens());
		//while (n.hasMoreTokens()) {
			//System.out.println(n.nextToken());
		//}
		lista = AnaliseSintatica.AnalisaPalavra("também");
	}
	
}
