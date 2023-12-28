package br.com.cjl.adapter.out.jpa.account;

import br.com.cjl.domain.Role;
import br.com.cjl.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "users")
public class AccountJpa {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String email;
    private String password;
    private Long empresaId;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<RoleJpa> roles = new ArrayList<>();

    public User toUser(){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setId(id);
        user.setRoles(getRoles().stream().map(RoleJpa::getName).map(Role::valueOf).collect(Collectors.toList()));
        return user;
    }
}
