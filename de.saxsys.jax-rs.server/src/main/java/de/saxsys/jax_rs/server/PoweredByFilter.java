package de.saxsys.jax_rs.server;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
@PoweredBy
public class PoweredByFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext,
			ContainerResponseContext responseContext) throws IOException {
		// add X-Powered-By header to response
		responseContext.getHeaders().add("X-Powered-By", "Jersey 2.0");
	}
}
