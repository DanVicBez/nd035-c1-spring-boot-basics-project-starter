package com.udacity.jwdnd.course1.cloudstorage.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.model.User;

@Mapper
public interface UserMapper {
	@Select("SELECT * "
		  + "FROM USERS "
		  + "WHERE username = #{username}")
	@Results({
		@Result(property = "id", column = "userid")
	})
	public User getUser(String username);

	@Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) "
		  + "VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
	@Options(useGeneratedKeys = true)
	public int insert(User user);
}
