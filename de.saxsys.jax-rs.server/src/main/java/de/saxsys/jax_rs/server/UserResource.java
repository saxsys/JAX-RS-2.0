package de.saxsys.jax_rs.server;

import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
public class UserResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JsonObject getUser() {
		return Json.createObjectBuilder() //
				.add("username", "sbley") //
				.add("entryDate", new Date().getTime()) //
				.build();
	}
}
