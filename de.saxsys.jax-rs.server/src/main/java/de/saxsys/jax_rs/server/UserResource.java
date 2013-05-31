package de.saxsys.jax_rs.server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/user")
public class UserResource {

	@GET
	public String getUser() {
		return "sbley";
	}
}
