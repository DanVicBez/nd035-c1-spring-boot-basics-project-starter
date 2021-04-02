package com.udacity.jwdnd.course1.cloudstorage.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;

@Mapper
public interface FileMapper {
	@Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) "
		  + "VALUES (#{name}, #{contentType}, #{size}, #{userId}, #{data})")
	public int insertFile(File file);
}
