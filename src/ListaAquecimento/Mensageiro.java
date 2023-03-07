package ListaAquecimento;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mensageiro {
	 
	public static int getDiaSemana(LocalDate date) {
	            DayOfWeek day = date.getDayOfWeek();
	            return day.getValue();
	        }
	  
	  public static String retornarProgramaPeloDia(){
		  
		  CentralDeInformacoes central = MetodosPersistencia.obterCentral("arquivo.xml");
		    
		  String saida = "|";
	       
		    ArrayList<ProgramaDeTv> programasFavoritos = central.getProgramasFavoritos();
	       
	        LocalDate hoje = LocalDate.now();
	        int diaSemana = getDiaSemana(hoje);
	        
	        if(programasFavoritos.size() != 0) {
	        	     
	        	for(int i =0; i < programasFavoritos.size(); i++) {
	        	
	        	switch (diaSemana) {
        		
	        	case 1:
        			if(programasFavoritos.get(i).getDias()[i] == DayOfWeek.MONDAY) {
        				saida += programasFavoritos.get(i).getNome() + "|";
        			}	
        		case 2:
        			if(programasFavoritos.get(i).getDias()[i] == DayOfWeek.TUESDAY) {
        				saida += programasFavoritos.get(i).getNome() + "|";
        			}	
        		case 3:
        			if(programasFavoritos.get(i).getDias()[i] == DayOfWeek.WEDNESDAY) {
        				saida += programasFavoritos.get(i).getNome() + "|";
        			}
        		case 4:
        			if(programasFavoritos.get(i).getDias()[i] == DayOfWeek.THURSDAY) {
        				saida += programasFavoritos.get(i).getNome() + "|";
        			}	
        		case 5:
        			if(programasFavoritos.get(i).getDias()[i] == DayOfWeek.FRIDAY) {
        				saida += programasFavoritos.get(i).getNome() + "|";
        			}	
        		case 6:
        			if(programasFavoritos.get(i).getDias()[i] == DayOfWeek.SATURDAY) {
        				saida += programasFavoritos.get(i).getNome() + "|";
        			}	
        		case 7:
        			if(programasFavoritos.get(i).getDias()[i] == DayOfWeek.SUNDAY) {
        				saida += programasFavoritos.get(i).getNome() + "|";
        			}
	        	}
	        }
	        }else 
    			return "Nenhuma programação no momento :|";
	        
			return saida;
	  }

	
	
	  public static void enviarProgramacaoDeHoje(String destinatario){
	        
	        
	        Properties props = new Properties();
	        props.put("mail.smtp.user","alunaifpbprojeto@gmail.com");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "25");
	        props.put("mail.debug", "true");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable","true");
	        props.put("mail.smtp.EnableSSL.enable","true");
	        props.put("mail.smtp.starttls.required", "true");
	        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
	     
	        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        props.setProperty("mail.smtp.socketFactory.fallback", "false");
	        props.setProperty("mail.smtp.port", "465");
	        props.setProperty("mail.smtp.socketFactory.port", "465");


	        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
	            protected PasswordAuthentication getPasswordAuthentication()
	            {
	                return new PasswordAuthentication("alunaifpbprojeto@gmail.com", "ruhqbmylbcaamokm");
	            }
	        });

	        session.setDebug(true);
	      
	        try {
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("alunaifpbprojeto@gmail.com"));
	            Address[] toUser = InternetAddress.parse(destinatario);
	            message.setRecipients(Message.RecipientType.TO, toUser);
	         
	            // cabeçalho do gmail
	            message.setSubject("Programação de hoje");
	            // corpo do e-mail
	            message.setText(retornarProgramaPeloDia());
	            Transport.send(message);

	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }

	    }

}
