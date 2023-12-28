package br.com.cjl.adapter.out.jpa;

import br.com.cjl.adapter.out.jpa.account.AccountJpa;
import br.com.cjl.adapter.out.jpa.account.AccountJpaRepository;
import br.com.cjl.adapter.out.jpa.account.RoleJpa;
import br.com.cjl.adapter.out.jpa.account.RoleJpaRepository;
import br.com.cjl.application.usecase.AccountRepository;
import br.com.cjl.application.usecase.account.SignupDTO;
import br.com.cjl.domain.Role;
import br.com.cjl.domain.User;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@RequiredArgsConstructor
public class AccountJpaRepositoryAdapter implements AccountRepository {
    private final AccountJpaRepository accountJpaRepository;
    private final RoleJpaRepository roleJpaRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        return accountJpaRepository.findByEmail(email).map(AccountJpa::toUser);
    }

    @Override
    public User signup(SignupDTO signupDTO) {
        AccountJpa accountJpa = new AccountJpa();
        accountJpa.setEmail(signupDTO.getEmail());
        accountJpa.setPassword(signupDTO.getPassword());
        accountJpa.setUsername(signupDTO.getUsername());
        accountJpa.setEmpresaId(1L);
        accountJpaRepository.save(accountJpa);
        accountJpa.getRoles().add(getAdminRole());
        return accountJpaRepository.save(accountJpa).toUser();
    }

    private RoleJpa getAdminRole() {
        return roleJpaRepository.findByName(Role.ADMIN.toString()).orElseThrow(
                () -> new NotFoundException("Não foi possível encontrar a Role")
        );
    }
}
