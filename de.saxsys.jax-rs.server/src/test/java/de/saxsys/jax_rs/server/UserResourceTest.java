package de.saxsys.jax_rs.server;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import de.saxsys.jax_rs.server.domain.User;

public class UserResourceTest extends JerseyTest {

	@Override
	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig(UserResource.class);
	}

	@Test
	public void getUser1_returnsUserBley() throws Exception {
		User user = target("user/1").request().get(User.class);
		assertThat(user.getUsername(), is("sbley"));
	}

	@Test
	public void getUser_throws404() throws Exception {
		try {
			target("user/2").request().get(User.class);
			fail();
		} catch (WebApplicationException e) {
			assertThat(e.getResponse().getStatus(), is(404));
		}
	}

	@Test
	public void putUser_returns200IfChanged() throws Exception {
		User u = new User();
		u.setUsername("kzickmann");
		Response response = target("user/1").request().put(
				Entity.entity(u, MediaType.APPLICATION_XML));
		assertThat(response.getStatus(), is(200));
	}

	@Test
	public void putUser_returns201IfCreated() throws Exception {
		User u = new User();
		u.setUsername("sschmeck");
		Response response = target("user/2").request().put(
				Entity.entity(u, MediaType.APPLICATION_XML));
		assertThat(response.getStatus(), is(201));
	}

	@Test
	public void deleteUser_returns200IfDeleted() throws Exception {
		Response response = target("user/1").request().delete();
		assertThat(response.getStatus(), is(200));
	}

	@Test
	public void deleteUser_returns404IfNotFound() throws Exception {
		Response response = target("user/2").request().delete();
		assertThat(response.getStatus(), is(404));
	}
}
