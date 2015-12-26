package match;

import java.util.StringTokenizer;

public class Comparacao {
	public static int Comparar(String str1, String str2){
		String texto1, texto2;
		StringTokenizer Valida1 = new StringTokenizer(str1);
		StringTokenizer Valida2 = new StringTokenizer(str2);
		if (Valida1.countTokens() >= Valida2.countTokens()){
			texto1 = str1;
			texto2 = str2;
		}else{
			texto1 = str2;
			texto2 = str1; 
		}
		int a = 0;
		float peso = 0;
		float valor = 0;
		float indice;
		String st1, st2;
		StringTokenizer Tokenstr1 = new StringTokenizer(texto2);
		while (Tokenstr1.hasMoreTokens()) {
			a = a + 1;
			st1 = Tokenstr1.nextToken();
			StringTokenizer Tokenstr2 = new StringTokenizer(texto1);
			if (a == 1) {
            	peso = peso + 6;
            }
            if (a == 2) {
            	peso = (float) (peso + 5);
            }
            if (a > 2) {
            	peso = (float) (peso + 1);
            }
			while (Tokenstr2.hasMoreTokens()) {
                st2 = Tokenstr2.nextToken();
                if (a == 1) {
                    if (st1.equals(st2)) {
                    	valor = valor + 6;
                    }
                }
                if (a == 2) {
                    if (st1.equals(st2)) {
                    	valor = (float) (valor + 5);
                    }
                }
                if (a > 2) {
                    if (st1.equals(st2)) {
                    	valor = (float) (valor + 1);
                    }
                }
           }
    }
    indice = (valor / peso) * 100;
    int b = (int)indice;
    return (int) b;
	}
	

}
