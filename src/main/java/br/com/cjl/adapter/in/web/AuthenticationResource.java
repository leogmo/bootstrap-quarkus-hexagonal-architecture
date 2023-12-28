package br.com.cjl.adapter.in.web;

import br.com.cjl.adapter.out.jpa.AccountJpaRepositoryAdapter;
import br.com.cjl.application.usecase.account.*;
import br.com.cjl.infrastructure.security.PBKDF2Encoder;
import io.quarkus.security.runtime.SecurityIdentityAssociation;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/user")
public class AuthenticationResource {

    @Inject
    PBKDF2Encoder passwordEncoder;

    @Inject
    LoginUseCase loginUseCase;

    @Inject
    SignupUseCase signupUseCase;

    @Inject
    PasswordRecoveryUseCase passwordRecoveryUseCase;

    @Inject
    AccountJpaRepositoryAdapter accountJpaRepositoryAdapter;

    @Inject
    SecurityIdentityAssociation identity;

    @PermitAll
    @POST
    @Path("/login") @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginDTO authRequest) {
        authRequest.password = passwordEncoder.encode(authRequest.password);
        try {
            return Response.ok(loginUseCase.login(authRequest)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @PermitAll
    @POST
    @Path("/passrecover") @Produces(MediaType.APPLICATION_JSON)
    public Response passRecover(PassRecoverDTO passRecoverDTO) throws Exception {
        passwordRecoveryUseCase.recover(passRecoverDTO);
        return Response.ok().build();
    }

    @PermitAll
    @POST
    @Path("/signup") @Produces(MediaType.APPLICATION_JSON)
    public Response signup(SignupDTO signupDTO){
        signupDTO.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        return Response.ok(signupUseCase.signup(signupDTO)).build();
    }

    @PermitAll
    @GET
    @Path("/passrecover/email") @Produces(MediaType.APPLICATION_JSON)
    public Response recoverEmail(SignupDTO signupDTO){
        return Response.ok(identity.getIdentity().getPrincipal().getName()).build();
    }

}