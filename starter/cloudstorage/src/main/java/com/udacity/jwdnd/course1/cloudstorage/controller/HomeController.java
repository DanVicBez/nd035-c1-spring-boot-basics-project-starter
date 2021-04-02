package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;

@Controller
@RequestMapping("home")
public class HomeController {
	@Autowired
	private FileService fileService;
	
	@Autowired
	private NoteService noteService;
	
	@Autowired
	private CredentialService credentialService;
	
	@Autowired
	private EncryptionService encryptionService;
	
	@GetMapping
	public String home(Authentication auth, Model model) {
		User user = (User) auth.getPrincipal();
		model.addAttribute("userId", user.getId());
		model.addAttribute("files", fileService.getAllFilesForUser(user));
		model.addAttribute("notes", noteService.getAllNotesForUser(user));
		model.addAttribute("credentials", credentialService.getAllCredentialsForUser(user));
		model.addAttribute("encryptionService", encryptionService);
		return "home";
	}
	
}
