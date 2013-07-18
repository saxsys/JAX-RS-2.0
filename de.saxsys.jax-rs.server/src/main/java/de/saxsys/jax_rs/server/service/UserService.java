package de.saxsys.jax_rs.server.service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.saxsys.jax_rs.server.domain.User;

public class UserService {

	private Map<Integer, User> users = new HashMap<>();

	public UserService() {
		User u = new User();
		u.setUsername("sbley");
		u.setEntryDate(new Date());
		users.put(1, u);
	}

	public User getUser(int id) {
		return users.get(id);
	}

	public User createUser(int id, User newUser) {
		return users.put(id, newUser);
	}

	public User removeUser(int id) {
		return users.remove(id);
	}

	public Collection<User> getAllUsers() {
		return users.values();
	}
}
