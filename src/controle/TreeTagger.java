package controle;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import org.annolab.tt4j.TokenHandler;
import org.annolab.tt4j.TreeTaggerWrapper;

import removerPontuacao.RemoverPontuacao;
import segmentacao.Tokenization;
import analiseSintatica.AnaliseSintatica;

public class TreeTagger {
	 private static List<String> lista;

	public static void main(String[] args) throws Exception
     {
		 lista = new ArrayList<String>();
		 String texto;
         //Tokenization teste = new Tokenization();
         //texto= RemoverPontuacao.removePontuacao("Arrascaeta precisa ganhar cerca de quatro quilos de m�sculo. � o que quer Vanderlei Luxemburgo, como disse o treinador ap�s a vit�ria contra o Palmeiras, por 2 a 1, no domingo. Acostumado com cerca de 30 partidas por temporada, no Uruguai, quando atuava pelo Defensor, o meia admite que precisa melhorar a condi��o f�sica para se adaptar ao calend�rio brasileiro.");
         texto = AnaliseSintatica.AnalisaPalavra("Ai!");
		 System.out.println(texto);
         //for(String temp : lista) {
        	 //System.out.println(temp);
 		//}
     }

}
