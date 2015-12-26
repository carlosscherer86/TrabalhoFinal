package removerPontuacao;

public class RemoverPontuacao {
	public static String removePontuacao(String pDescricao) {
		String passa = pDescricao;
		//passa = passa.replaceAll("[ÂÀÁÄÃ]", "A");
		//passa = passa.replaceAll("[âãàáä]", "a");
		//passa = passa.replaceAll("[ÊÈÉË]", "E");
		//passa = passa.replaceAll("[êèéë]", "e");
		//passa = passa.replaceAll("ÎÍÌÏ", "I");
		//passa = passa.replaceAll("îíìï", "i");
		//passa = passa.replaceAll("[ÔÕÒÓÖ]", "O");
		//passa = passa.replaceAll("[ôõòóö]", "o");
		//passa = passa.replaceAll("[ÛÙÚÜ]", "U");
		//passa = passa.replaceAll("[ûúùü]", "u");
		//passa = passa.replaceAll("Ç", "C");
		//passa = passa.replaceAll("ç", "c");
		//passa = passa.replaceAll("[ıÿ]", "y");
		//passa = passa.replaceAll("İ", "Y");
		//passa = passa.replaceAll("ñ", "n");
		//passa = passa.replaceAll("Ñ", "N");
		//passa = passa.replaceAll("[-+=*&amp;%$#@!_]", "");
		passa = passa.replaceAll("['\"]", "");
		passa = passa.replaceAll("[<>()\\{\\}]", "");
		passa = passa.replaceAll("['\\\\.,()|/]", "");
		passa = passa.replaceAll("[^!-ÿ]{1}[^ -ÿ]{0,}[^!-ÿ]{1}|[^!-ÿ]{1}", " ");
		return passa;
	}

}
