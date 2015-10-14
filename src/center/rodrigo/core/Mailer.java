package center.rodrigo.core;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mailer {

    public void sendEmail(String para, String anexo, String texto, String Assunto) {

        try {

            Properties props = new Properties();
            MailAuthenticator auth = new MailAuthenticator();

            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getInstance(props, auth);
            MimeMessage msg = new MimeMessage(session);

            // /////////////////////////////////////////////////////////
            msg.setFrom(new InternetAddress("SEU_EMAIL@gmail.com"));
            // /////////////////////////////////////////////////////////

            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(para));
            msg.setSentDate(new Date());
            msg.setText(texto);
            msg.setSubject(Assunto);

            /* ANEXO */
            MimeBodyPart bodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            DataSource source = new FileDataSource(anexo);
            bodyPart.setDataHandler(new DataHandler(source));
            bodyPart.setFileName(anexo);
            multipart.addBodyPart(bodyPart);

            msg.setContent(multipart);

            Transport.send(msg);
            
            System.out.println("Email enviado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class MailAuthenticator extends Authenticator {

        private PasswordAuthentication authentication;

        public MailAuthenticator() {
            // ///////////////////////////////////////////////
            String username = "SEU_EMAIL@gmail.com";
            String password = "SUA_SENHA";
            // ///////////////////////////////////////////////
            authentication = new PasswordAuthentication(username, password);
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return authentication;
        }
    }
}
