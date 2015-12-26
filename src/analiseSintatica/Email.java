package analiseSintatica;

import javax.swing.JOptionPane;
import org.apache.commons.mail.EmailException;  
import org.apache.commons.mail.SimpleEmail; 
public class Email {
     public static void main(String[] args) {  
        SimpleEmail email = new SimpleEmail();  
        try {  
        email.setDebug(true);  
        email.setHostName("smtp.gmail.com");  
        email.setAuthentication("prototipo1986@gmail.com", "98197860");  
        email.setSSL(true);
        email.setTLS(true);
        email.addTo("prototipo1986@gmail.com"); //pode ser qualquer um email  
        email.setFrom("prototipo1986@gmail.com"); //aqui necessita ser o email que voce fara a autenticacao  
        email.setSubject("teste");  
        email.setMsg("rafa_tkd_tronco@hotmail.com");  
        email.send();  
        } catch (EmailException e) {  
        JOptionPane.showMessageDialog(null, "Não enviou!");
        }   
    }  
}
