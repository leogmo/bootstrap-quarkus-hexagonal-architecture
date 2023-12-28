package br.com.cjl.adapter.in.web;

import br.com.cjl.infrastructure.Message;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/hello")
public class GreetingResource {
    @RolesAllowed("USER")
    @GET @Path("/user") @Produces(MediaType.APPLICATION_JSON)
    public Response user() {
        return Response.ok(new Message("Content for user")).build();
    }

    @RolesAllowed("ADMIN")
    @GET @Path("/admin") @Produces(MediaType.APPLICATION_JSON)
    public Response admin() {
        return Response.ok(new Message("Content for admin")).build();
    }

    @RolesAllowed({"USER", "ADMIN"})
    @GET @Path("/user-or-admin") @Produces(MediaType.APPLICATION_JSON)
    public Response userOrAdmin() {
        return Response.ok(new Message("Content for user or admin")).build();
    }
}
