package de.saxsys.jax_rs.server;

import java.util.Date;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import de.saxsys.jax_rs.server.domain.User;

@Path("/")
@PermitAll
public class UserResource {

	@Path("/user")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@RolesAllowed("user")
	public User getUser() {
		User u = new User();
		u.setUsername("sbley");
		u.setEntryDate(new Date());
		return u;
	}

	@Path("/admin")
	@GET
	@RolesAllowed("admin")
	public String getInfo(@Context SecurityContext sc) {
		return "You are " + sc.getUserPrincipal();
	}

	@Path("/hello")
	@GET
	public String getHelloWorld() {
		return "Hello World!";
	}
}
