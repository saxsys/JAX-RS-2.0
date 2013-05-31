package de.saxsys.jax_rs.server;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;

public class ExampleServer {

	/** http://localhost:8180/server/<resource> */
	public static final URI BASE_URI = UriBuilder.fromUri("http://localhost").path("server")
			.port(8180).build();

	public static void main(String[] args) throws IOException {
		// Start server
		HttpServer httpServer = startServer();
		System.out.printf("Jersey app started at %s. Hit enter to stop it...\n", BASE_URI);

		// Wait for console input
		System.in.read();

		// Stop server
		httpServer.stop();
	}

	protected static HttpServer startServer() throws IOException {
		System.out.println("Starting grizzly...");

		// Configure resource package
		ResourceConfig conf = new PackagesResourceConfig("de.saxsys.jax_rs.server");

		// Allow POJO/JSON mapping
		// conf.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, true);

		// Create server instance
		return GrizzlyServerFactory.createHttpServer(BASE_URI, conf);
	}

}
