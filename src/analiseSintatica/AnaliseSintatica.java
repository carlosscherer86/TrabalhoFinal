package analiseSintatica;

import static java.util.Arrays.asList;

import java.io.IOException;

import org.annolab.tt4j.TokenHandler;
import org.annolab.tt4j.TreeTaggerException;
import org.annolab.tt4j.TreeTaggerWrapper;

public class AnaliseSintatica {
	private static String abrev;

	public static String AnalisaPalavra(String pPalavra) throws IOException,
			TreeTaggerException {
		abrev="";
		System.setProperty("treetagger.home", "/opt/treetagger");
		TreeTaggerWrapper<String> tt = new TreeTaggerWrapper<String>();
		try {
			tt.setModel("/opt/treetagger/models/portuguese.par");
			tt.setHandler(new TokenHandler<String>() {
				public void token(String token, String pos, String lemma) {
					abrev = pos;
				}
			});
			tt.process(new String[] { pPalavra });
		} finally {
			tt.destroy();
		}
		
		return abrev.substring(0, 1);
	}
}
