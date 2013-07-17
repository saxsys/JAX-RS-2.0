package de.saxsys.jax_rs.server;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import de.saxsys.jax_rs.server.domain.User;

@Path("/user")
public class UserResource {

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public User getUser() {
		User u = new User();
		u.setUsername("sbley");
		u.setAge(20);
		return u;
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response registerUser( //
			@NotNull @FormParam("username") String username, //
			@Min(18) @FormParam("age") String age) {
		System.out.println("Save user " + username);
		return Response.created(UriBuilder.fromUri("http://localhost:8180/server/user/1").build())
				.build();
	}
}
