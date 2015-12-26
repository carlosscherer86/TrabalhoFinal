package radicalizacao;
import ptstemmer.Stemmer;
import ptstemmer.exceptions.PTStemmerException;
import ptstemmer.implementations.OrengoStemmer;

public class Radicalizacao {
	public static String RadicalizarPalavra(String pPalavra) {
		Stemmer stemmer = null;
		try {
			stemmer = new OrengoStemmer();
		} catch (PTStemmerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stemmer.getWordStem(pPalavra);
	}

}
