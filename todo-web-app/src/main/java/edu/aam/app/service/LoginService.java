package edu.aam.app.service;

public class LoginService {
	public boolean isUserValid(String user, String password) {
		return user.equalsIgnoreCase("user") && password.equals("dummy");
	}
}
