package br.com.cjl.application.usecase.account;

import br.com.cjl.application.usecase.AccountRepository;
import br.com.cjl.application.usecase.EmailSender;
import br.com.cjl.domain.User;
import br.com.cjl.infrastructure.security.TokenUtils;
import io.quarkus.mailer.Mail;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class PasswordRecoveryUseCase {
    private final AccountRepository accountRepository;
    private final EmailSender emailSender;

    public void recover(@Valid PassRecoverDTO passRecoverDTO) throws Exception {
        Optional<User> user = accountRepository.findByEmail(passRecoverDTO.getEmail());
        if (user.isEmpty()){
            throw new Exception("Email não encontrado na base de dados");
        }
        String token = TokenUtils.generateTokenPassRecover(user.get().getEmail(), user.get().getRoles(), 86400L);

        emailSender.sendRecoveryEmail(token, user.get(), passRecoverDTO.getUrl());
    }
}
