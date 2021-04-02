package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;

@Controller
@RequestMapping("note")
public class NoteController {
	@Autowired
	private NoteService noteService;
	
	@PostMapping
	public String submitNote(Note note, RedirectAttributes ra) {
		if (noteService.addNote(note) == 1) {
			ra.addFlashAttribute("message", "Successfully submitted note");
		} else {
			ra.addFlashAttribute("error", "An error occurred while submitting the note");
		}

		return "redirect:/home";
	}
	
	@GetMapping("delete/{id}")
	public String deleteNote(@PathVariable int id, RedirectAttributes ra) {
		if (noteService.deleteNoteById(id) == 1) {
			ra.addFlashAttribute("message", "Successfully deleted note");
		} else {
			ra.addFlashAttribute("error", "An error occurred while deleting the note");
		}

		return "redirect:/home";
	}
}
