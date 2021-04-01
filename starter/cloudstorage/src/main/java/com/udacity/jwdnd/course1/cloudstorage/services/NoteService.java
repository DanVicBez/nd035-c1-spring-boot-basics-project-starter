package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;

@Service
public class NoteService {
	@Autowired
	private NoteMapper noteMapper;
	
	public int addNote(Note note) {
		return noteMapper.insert(note);
	}
	
	public List<Note> getAllNotesForUser(User user) {
		return noteMapper.getAllNotesForUser(user.getId());
	}
}
