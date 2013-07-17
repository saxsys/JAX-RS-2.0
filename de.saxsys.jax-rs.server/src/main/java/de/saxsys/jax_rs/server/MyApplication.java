package de.saxsys.jax_rs.server;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

@ApplicationPath("/")
public class MyApplication extends ResourceConfig {

	public MyApplication() {
		super();
		packages("de.saxsys.jax_rs.server");
		register(RolesAllowedDynamicFeature.class);
	}
}
