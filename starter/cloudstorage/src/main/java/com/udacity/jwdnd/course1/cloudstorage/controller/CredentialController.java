package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;

@Controller
@RequestMapping("credential")
public class CredentialController {
	@Autowired
	private CredentialService credentialService;

	@PostMapping
	public String submitCredential(Credential credential) {
		credentialService.addCredential(credential);
		return "redirect:/home";
	}
}
