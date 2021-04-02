package com.udacity.jwdnd.course1.cloudstorage.services;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;

@Service
public class CredentialService {
	@Autowired
	private CredentialMapper credentialMapper;
	
	@Autowired
	private EncryptionService encryptionService;

	public int addCredential(Credential credential) {
		Credential existing = getCredentialById(credential.getId());
		if (existing == null) {
			// generate key
			SecureRandom random = new SecureRandom();
			byte[] key = new byte[16];
			random.nextBytes(key);
			String encodedKey = Base64.getEncoder().encodeToString(key);
			credential.setKey(encodedKey);
			
			// encrypt password
			String encrypted = encryptionService.encryptValue(credential.getPassword(), encodedKey);
			credential.setPassword(encrypted);

			return credentialMapper.insert(credential);
		} else {
			credential.setKey(existing.getKey());

			// encrypt password
			String encrypted = encryptionService.encryptValue(credential.getPassword(), existing.getKey());
			credential.setPassword(encrypted);

			return credentialMapper.update(credential);
		}
	}
	
	public Credential getCredentialById(int id) {
		return credentialMapper.getCredentialById(id);
	}
	
	public List<Credential> getAllCredentialsForUser(User user) {
		return credentialMapper.getAllCredentialsForUser(user.getId());
	}
}
