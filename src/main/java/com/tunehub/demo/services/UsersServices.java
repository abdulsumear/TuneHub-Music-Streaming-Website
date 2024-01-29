package com.tunehub.demo.services;

import com.tunehub.demo.entities.Users;

public interface UsersServices {
	public String addUsers(Users user);

	public boolean emailExists(String email);
	
	public boolean validateUser(String email, String password);

	public String getRole(String email);
	
	public Users getUser(String email);
	
	public void updateUser(Users user);
}
