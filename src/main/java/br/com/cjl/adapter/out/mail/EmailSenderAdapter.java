package br.com.cjl.adapter.out.mail;

import br.com.cjl.application.usecase.EmailSender;
import br.com.cjl.domain.User;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class EmailSenderAdapter implements EmailSender {
    @Inject
    Mailer mailer;

    @Override
    public void sendRecoveryEmail(String token, User user, String url) {
        mailer.send(Mail.withText(user.getEmail(), "Recuperar Senha", "Acesse para redefinir a senha: "+ url + "/resetpassword?token=" + token));
    }
}
