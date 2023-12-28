package br.com.cjl.application.usecase.account;

import br.com.cjl.application.usecase.AccountRepository;
import br.com.cjl.domain.User;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;

import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class UpdatePasswordUseCase {
    private final AccountRepository accountRepository;

    public void update(UpdatePasswordDTO updatePasswordDTO) throws Exception {
        Optional<User> user = accountRepository.findByEmail(updatePasswordDTO.getEmail());
        if (user.isEmpty()) {
            throw new Exception("Email não encontrado na base de dados");
        }

        user.get().setPassword(updatePasswordDTO.getPassword());
        accountRepository.save(user.get());
    }
}
