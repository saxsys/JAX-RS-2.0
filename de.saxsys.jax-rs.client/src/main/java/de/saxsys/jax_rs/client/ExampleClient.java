package de.saxsys.jax_rs.client;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

public class ExampleClient {

	private static final URI BASE_URI = UriBuilder.fromUri("http://localhost:8180").path("server")
			.build();

	private Client client;

	public static void main(String[] args) {
		new ExampleClient().launch();
	}

	public ExampleClient() {
		client = ClientBuilder.newClient();
	}

	private void launch() {
		// http://localhost:8180/server/user/1
		WebTarget userTarget = client.target(UriBuilder.fromUri(BASE_URI).path("user").path("{id}")
				.build(1));
		// GET
		Response response = userTarget.request(MediaType.APPLICATION_XML).get();

		String userXml = response.readEntity(String.class);
		System.out.println(userXml);
	}

}
