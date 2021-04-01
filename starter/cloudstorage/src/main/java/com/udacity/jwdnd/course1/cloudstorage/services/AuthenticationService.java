package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.model.User;

@Service
public class AuthenticationService implements AuthenticationProvider {
	@Autowired
	HashService hashService;
	
	@Autowired
	UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		if (userService.isUsernameAvailable(username)) {
			return null;
		}
	
		String password = authentication.getCredentials().toString();
		User user = userService.getUser(username);
		if (user == null) {
			return null;
		}

		String hashedPassword = hashService.getHashedValue(password, user.getSalt());
		if (hashedPassword.equals(user.getPassword())) {
			return new UsernamePasswordAuthenticationToken(user, password, new ArrayList<>());
		}
		
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
