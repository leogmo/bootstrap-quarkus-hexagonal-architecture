package br.com.cjl.application.usecase.account;

import br.com.cjl.application.usecase.AccountRepository;
import br.com.cjl.domain.User;
import br.com.cjl.infrastructure.security.TokenUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
@ApplicationScoped
@RequiredArgsConstructor
public class LoginUseCase {
    private final AccountRepository accountRepository;
    public LoginResponseDTO login(@Valid LoginDTO loginDTO) {
        Optional<User> user = accountRepository.findByEmail(loginDTO.email);

        if (user.isPresent()){
            if (user.get().matchesPassword(loginDTO.password)){
                try {
                    return new LoginResponseDTO(TokenUtils.generateToken(user.get().getEmail(), user.get().getRoles()));
                } catch (Exception e){
                    throw new SecurityException("Erro ao gerar o token");
                }
            }
        } else {
            throw new SecurityException("Usuário não encontrado");
        }
        return null;
    }
}
