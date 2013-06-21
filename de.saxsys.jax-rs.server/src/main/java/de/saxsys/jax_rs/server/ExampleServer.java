package de.saxsys.jax_rs.server;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class ExampleServer {

	/** http://localhost:8180/server/<resource> */
	public static final URI BASE_URI = UriBuilder.fromUri("http://localhost").path("server")
			.port(8180).build();

	public static void main(String[] args) throws IOException {
		System.out.println("Starting grizzly...");

		// Start server
		HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, createConfig());
		System.out.printf("Jersey app started at %s. Hit enter to stop it...\n", BASE_URI);

		// Wait for console input
		System.in.read();

		// Stop server
		httpServer.stop();
	}

	private static ResourceConfig createConfig() {
		return new ResourceConfig() //
				.packages("de.saxsys.jax_rs.server") //
				.register(JacksonFeature.class); // Allow JSON mapping
	}

}
