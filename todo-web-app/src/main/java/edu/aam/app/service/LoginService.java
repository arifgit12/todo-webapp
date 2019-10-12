package edu.aam.app.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
	public boolean isUserValid(String user, String password) {
		return user.equalsIgnoreCase("springuser") && password.equals("dummy");
	}
}
