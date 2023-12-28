package br.com.cjl.application.usecase;

import br.com.cjl.domain.User;

public interface EmailSender {
    void sendRecoveryEmail(String token, User user, String url);
}
