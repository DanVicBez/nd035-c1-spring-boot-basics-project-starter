package com.udacity.jwdnd.course1.cloudstorage.services;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private HashService hashService;

	public boolean isUsernameAvailable(String username) {
		return userMapper.getUser(username) == null;
	}

	public int createUser(User user) {
		byte[] salt = new byte[16];
		new SecureRandom().nextBytes(salt);

		String encodedSalt = Base64.getEncoder().encodeToString(salt);
		user.setSalt(encodedSalt);

		String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
		user.setPassword(hashedPassword);

		return userMapper.insert(user);
	}

	public User getUser(String username) {
		return userMapper.getUser(username);
	}
}
