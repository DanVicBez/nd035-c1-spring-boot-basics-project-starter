package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.model.File;

@Mapper
public interface FileMapper {
	@Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) "
		  + "VALUES (#{name}, #{contentType}, #{size}, #{userId}, #{data})")
	public int insertFile(File file);

	@Select("SELECT * "
		  + "FROM FILES "
		  + "WHERE userId=#{userId}")
	@Results({
		@Result(property = "id", column = "fileId"),
		@Result(property = "name", column = "filename"),
		@Result(property = "size", column = "filesize"),
		@Result(property = "data", column = "filedata")
	})
	public List<File> getAllFilesForUser(int userId);

	@Select("SELECT * "
			+ "FROM FILES "
			+ "WHERE fileId=#{id}")
	@Results({
		@Result(property = "id", column = "fileId"),
		@Result(property = "name", column = "filename"),
		@Result(property = "size", column = "filesize"),
		@Result(property = "data", column = "filedata")
	})
	public File getFileById(int id);
}
