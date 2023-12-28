package br.com.cjl.adapter.out.jpa.account;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleJpaRepository  extends CrudRepository<RoleJpa, Long> {
    Optional<RoleJpa> findByName(String name);
}
