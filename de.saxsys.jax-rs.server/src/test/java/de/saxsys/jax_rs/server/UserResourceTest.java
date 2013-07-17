package de.saxsys.jax_rs.server;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

public class UserResourceTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new ResourceConfig(UserResource.class);
	}

	@Test
	public void postAdultUser_returns204() {
		Response response = target("user").request().post(
				Entity.form(new Form().param("username", "sbley").param("age", "30")));

		assertThat(response.getStatus(), is(Response.Status.CREATED.getStatusCode()));
	}

	@Test
	public void postUserAgedUnder18_returns500() {
		Response response = target("user").request().post(
				Entity.form(new Form().param("username", "sbley").param("age", "16")));

		assertThat(response.getStatus(), is(Response.Status.BAD_REQUEST.getStatusCode()));
	}
}
