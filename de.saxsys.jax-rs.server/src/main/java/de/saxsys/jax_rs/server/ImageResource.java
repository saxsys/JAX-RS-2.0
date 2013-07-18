package de.saxsys.jax_rs.server;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

public class ImageResource {

	@GET
	@Produces("image/png")
	public Response getImage(@PathParam("id") int userId) {
		try {
			return Response.ok(new File(getUri("image1.png"))).build();
		} catch (URISyntaxException e) {
			return Response.noContent().build();
		}
	}

	private URI getUri(String filename) throws URISyntaxException {
		return getClass().getClassLoader().getResource(filename).toURI();
	}
}
