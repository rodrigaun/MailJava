package center.rodrigo.main;

import center.rodrigo.core.Mailer;

public class Main {

    public static void main(String[] args) {

        // importar mail.jar e colocar email e senha na classe Mailer
        // (no caso do Gmail, autorizar outros app nas configuraçoes > Segurança da conta)

        Mailer mail = new Mailer();
        mail.sendEmail("rodrigoa_garcia@hotmail.com", "homer.png", "Texto Corpo do email", "Assunto");
    }
}
