package segmentacao;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Tokenization {
	private static List<String> lista;

	public static List<String> RetornaTokens(String pDescricacao) {
		StringTokenizer n = new StringTokenizer(pDescricacao);
		lista = new ArrayList<String>();
		while (n.hasMoreTokens()) {
			lista.add(n.nextToken());
		}
		return lista;
	}

}
