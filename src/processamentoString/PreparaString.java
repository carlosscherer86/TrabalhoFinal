package processamentoString;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import analiseSintatica.AnaliseSintatica;
import radicalizacao.Radicalizacao;
import removerPontuacao.RemoverPontuacao;
import segmentacao.Tokenization;
import sinonimos.Sinonimo;
import stopwords.StopWord;

public class PreparaString {
	public String Descricao;
	private static List<String> listaTemp;
	private static List<String> lista;
	
	public PreparaString (String pDescricao) throws IOException{
		String lTag;
		this.Descricao = "";
		pDescricao = RemoverPontuacao.removePontuacao(pDescricao);
		listaTemp = Tokenization.RetornaTokens(pDescricao);
		lista = new ArrayList<String>();
		for (String string : listaTemp) {
			if (!StopWord.BuscaStopWord(string)){				
				try {
					//lTag= AnaliseSintatica.AnalisaPalavra(string);
					string = Sinonimo.BuscaSinonimo(string);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				string = Radicalizacao.RadicalizarPalavra(string);
				lista.add(string);
				Descricao= Descricao + string;
			}
			Descricao = Descricao +" ";
		}
		
		
	}

}
