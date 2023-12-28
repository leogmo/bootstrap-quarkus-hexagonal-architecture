package br.com.cjl.adapter.out.jpa.account;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "roles")
public class RoleJpa {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
