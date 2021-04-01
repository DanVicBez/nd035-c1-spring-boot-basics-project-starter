package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;

@Mapper
public interface NoteMapper {
	@Insert("INSERT INTO NOTES (notetitle, notedescription, userid) "
		  + "VALUES (#{title}, #{description}, #{userId})")
	@Options(useGeneratedKeys = true)
	public int insert(Note note);
	
	@Select("SELECT * "
		  + "FROM NOTES "
		  + "WHERE userid = #{userId}")
	@Results({
		@Result(property = "id", column = "noteid"),
		@Result(property = "title", column = "notetitle"),
		@Result(property = "description", column = "notedescription")
	})
	public List<Note> getAllNotesForUser(int userId);
}
