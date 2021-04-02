package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;

@Mapper
public interface CredentialMapper {
	@Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) "
		  + "VALUES (#{url}, #{username}, #{key}, #{password}, #{userId})")
	@Options(useGeneratedKeys = true)
	public int insert(Credential credential);
	
	@Update("UPDATE CREDENTIALS "
		  + "SET url = #{url}, username = #{username}, key = #{key}, password = #{password} "
		  + "WHERE credentialid = #{id}")
	public int update(Credential credential);
	
	@Select("SELECT * "
		  + "FROM CREDENTIALS "
		  + "WHERE credentialid = #{id}")
	@Results({
		@Result(property = "id", column = "credentialid")
	})
	public Credential getCredentialById(int id);
	
	@Select("SELECT * "
		  + "FROM CREDENTIALS "
		  + "WHERE userid = #{userId}")
	@Results({
		@Result(property = "id", column = "credentialid")
	})
	public List<Credential> getAllCredentialsForUser(int userId);
	
	@Delete("DELETE FROM CREDENTIALS "
		  + "WHERE credentialid = #{id}")
	public int deleteById(int id);
}
