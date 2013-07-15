package de.saxsys.jax_rs.server;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.saxsys.jax_rs.server.domain.User;

@Path("/")
public class UserResource {

	@Path("/user")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public User getUser() {
		User u = new User();
		u.setUsername("sbley");
		u.setEntryDate(new Date());
		return u;
	}

	@Path("/hello")
	@GET
	@PoweredBy
	public String helloWorld() {
		return "Hello World";
	}
}
