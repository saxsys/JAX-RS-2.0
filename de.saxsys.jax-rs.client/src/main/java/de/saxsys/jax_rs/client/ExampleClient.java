package de.saxsys.jax_rs.client;

import java.net.URI;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import de.saxsys.jax_rs.server.domain.User;

public class ExampleClient {

	private static final URI BASE_URI = UriBuilder.fromUri("http://localhost:8180").path("server")
			.build();

	private Client client = ClientBuilder.newClient();

	public static void main(String[] args) {
		new ExampleClient().launch();
	}

	private void launch() {
		getUser(1);
		getUser(2); // 404
	}

	private void getUser(int id) {
		// http://localhost:8180/server/user/1
		WebTarget userTarget = client.target(UriBuilder.fromUri(BASE_URI).path("user").path("{id}")
				.build(id));
		// GET
		try {
			User user = userTarget.request(MediaType.APPLICATION_XML).get(User.class);
			System.out.println(user);
		} catch (WebApplicationException e) {
			System.err.println(e.getMessage());
		}
	}

}
