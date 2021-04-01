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
		if (getNoteById(note.getId()) == null) {
			return noteMapper.insert(note);
		} else {
			return noteMapper.update(note);
		}
	}
	
	public Note getNoteById(int id) {
		return noteMapper.getNoteById(id);
	}
	
	public List<Note> getAllNotesForUser(User user) {
		return noteMapper.getAllNotesForUser(user.getId());
	}
}
