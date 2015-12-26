package match;

import java.util.StringTokenizer;

import uk.ac.shef.wit.simmetrics.similaritymetrics.AbstractStringMetric;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Jaro;
import modelo.TbDemanda;
import modelo.TbMatch;
import modelo.TbOferta;
import ModeloRepositorio.TbMatchRepository;
import api.Email;
import api.GoogleMapsDirections;

public class AlgoritmoComparacao {

	public static void main(String[] args) {
		Email email = new Email();
		TbOferta oferta = new TbOferta();
		TbDemanda demanda = new TbDemanda();		
		email.enviarEmail(oferta, demanda);
		
		/*int similaridade;
		int similaridade2;
		String oferta, demanda;
		oferta = "comput cpu dual cor hd 500gb memor ram 4gb leitor sd + wi-f";
		demanda = "comput 4gb ram 500gb hd";
		similaridade = Similaridade.SimilaridadeCosseno(oferta, demanda);
		similaridade2 = (Comparacao.Comparar(oferta, demanda));
		System.out.println("Cosseno");
		System.out.println(similaridade);
		System.out.println((similaridade + similaridade2) / 2);

		similaridade = Similaridade.DistanciaEuclidiana(oferta, demanda);
		similaridade2 = (Comparacao.Comparar(oferta, demanda));
		System.out.println("Euclidiana");
		System.out.println(similaridade);
		System.out.println((similaridade + similaridade2) / 2);

		similaridade = Similaridade.SmithWaterman(oferta, demanda);
		similaridade2 = (Comparacao.Comparar(oferta, demanda));
		System.out.println("Smith Waterman");
		System.out.println(similaridade);
		System.out.println((similaridade + similaridade2) / 2);

		similaridade = Similaridade.Jaro(oferta, demanda);
		similaridade2 = (Comparacao.Comparar(oferta, demanda));
		System.out.println("Jaro");
		System.out.println(similaridade);
		System.out.println((similaridade + similaridade2) / 2);*/

	}

}
