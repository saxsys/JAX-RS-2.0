package de.saxsys.jax_rs.server;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import de.saxsys.jax_rs.server.domain.User;
import de.saxsys.jax_rs.server.service.UserService;

@Path("/user")
public class UserResource {

	private UserService userService = new UserService();

	@Context
	private UriInfo uriInfo;

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getUser(@PathParam("id") int id, @Context Request request) {
		User user = userService.getUser(id);
		if (null == user) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		// set max-age
		CacheControl cc = new CacheControl();
		cc.setMaxAge(10);
		cc.setPrivate(true);
		// create tag
		EntityTag etag = new EntityTag(user.getUsername()); // use hashCode()
		ResponseBuilder builder = request.evaluatePreconditions(etag);
		if (null == builder) {
			// resource is out of date
			builder = Response.ok(user);
			builder.tag(etag);
		}

		return builder.cacheControl(cc).build();
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_XML)
	public Response createUser(@PathParam("id") int id, User newUser) {
		User oldUser = userService.createUser(id, newUser);
		if (oldUser != null) {
			return Response.ok().build();
		} else {
			return Response.created(uriInfo.getRequestUri()).build();
		}
	}

	@DELETE
	@Path("{id}")
	public Response deleteUser(@PathParam("id") int id) {
		User oldUser = userService.removeUser(id);
		if (null == oldUser) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return Response.ok().build();
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Collection<User> getUsers() {
		return userService.getAllUsers();
	}
}
