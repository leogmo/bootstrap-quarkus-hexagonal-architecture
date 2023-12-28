package br.com.cjl.application.usecase.account;

import br.com.cjl.application.usecase.AccountRepository;
import br.com.cjl.application.usecase.account.SignupDTO;
import br.com.cjl.domain.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class SignupUseCase {
    private final AccountRepository accountRepository;

    public User signup(@Valid SignupDTO signupDTO) {
        return accountRepository.signup(signupDTO);
    }
}
