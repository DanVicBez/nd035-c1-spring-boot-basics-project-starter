package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
@RequestMapping("home")
public class HomeController {
	@Autowired
	private NoteService noteService;

	@Autowired
	private UserService userService;

	@GetMapping
	public String home() {
		return "home";
	}
	
	@PostMapping
	public String uploadNote(Authentication auth, Note note) {
		User user = userService.getUser(auth.getName());
		note.setUserId(user.getId());
		noteService.addNote(note);
		return "home";
	}
}
