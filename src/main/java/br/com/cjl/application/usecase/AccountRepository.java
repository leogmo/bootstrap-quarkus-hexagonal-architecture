package br.com.cjl.application.usecase;

import br.com.cjl.application.usecase.account.SignupDTO;
import br.com.cjl.domain.User;

import java.util.Optional;

public interface AccountRepository {
    Optional<User> findByEmail(String username);
    User signup(SignupDTO signupDTO);
}
