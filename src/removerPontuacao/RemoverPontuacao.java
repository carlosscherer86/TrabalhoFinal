package removerPontuacao;

public class RemoverPontuacao {
	public static String removePontuacao(String pDescricao) {
		String passa = pDescricao;
		//passa = passa.replaceAll("[�����]", "A");
		//passa = passa.replaceAll("[�����]", "a");
		//passa = passa.replaceAll("[����]", "E");
		//passa = passa.replaceAll("[����]", "e");
		//passa = passa.replaceAll("����", "I");
		//passa = passa.replaceAll("����", "i");
		//passa = passa.replaceAll("[�����]", "O");
		//passa = passa.replaceAll("[�����]", "o");
		//passa = passa.replaceAll("[����]", "U");
		//passa = passa.replaceAll("[����]", "u");
		//passa = passa.replaceAll("�", "C");
		//passa = passa.replaceAll("�", "c");
		//passa = passa.replaceAll("[��]", "y");
		//passa = passa.replaceAll("�", "Y");
		//passa = passa.replaceAll("�", "n");
		//passa = passa.replaceAll("�", "N");
		//passa = passa.replaceAll("[-+=*&amp;%$#@!_]", "");
		passa = passa.replaceAll("['\"]", "");
		passa = passa.replaceAll("[<>()\\{\\}]", "");
		passa = passa.replaceAll("['\\\\.,()|/]", "");
		passa = passa.replaceAll("[^!-�]{1}[^ -�]{0,}[^!-�]{1}|[^!-�]{1}", " ");
		return passa;
	}

}
