package br.com.cjl.application.usecase.account;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginDTO {

    public String email;
    public String password;
}