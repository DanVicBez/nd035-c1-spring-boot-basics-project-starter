package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;

@Controller
@RequestMapping("credential")
public class CredentialController {
	@Autowired
	private CredentialService credentialService;

	@PostMapping
	public String submitCredential(Credential credential, RedirectAttributes ra) {
		if (credentialService.addCredential(credential) == 1) {
			ra.addFlashAttribute("message", "Successfully submitted credential");
		} else {
			ra.addFlashAttribute("error", "An error occurred while submitting the credential");
		}

		return "redirect:/home";
	}
	
	@GetMapping("delete/{id}")
	public String deleteCredential(@PathVariable int id, RedirectAttributes ra) {
		if (credentialService.deleteCredentialById(id) == 1) {
			ra.addFlashAttribute("message", "Successfully deleted credential");
		} else {
			ra.addFlashAttribute("error", "An error occurred while deleting the credential");
		}

		return "redirect:/home";
	}
}
