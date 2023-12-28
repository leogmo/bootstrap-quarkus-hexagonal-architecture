package br.com.cjl.adapter.out.jpa.account;

import br.com.cjl.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountJpaRepository extends CrudRepository<AccountJpa, Long> {
    Optional<AccountJpa> findByEmail(String email);
}
