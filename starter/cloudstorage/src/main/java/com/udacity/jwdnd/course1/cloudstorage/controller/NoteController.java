package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;

@Controller
@RequestMapping("note")
public class NoteController {
	@Autowired
	private NoteService noteService;

	@PostMapping
	public String submitNote(Note note) {
		noteService.addNote(note);
		return "redirect:/home";
	}
	
	@GetMapping("delete/{id}")
	public String deleteNote(@PathVariable int id) {
		noteService.deleteNoteWithId(id);
		return "redirect:/home";
	}
}
