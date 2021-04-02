package com.udacity.jwdnd.course1.cloudstorage.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;

@Service
public class FileService {
	@Autowired
	private FileMapper fileMapper;

	public int uploadFile(MultipartFile multipartFile, int userId) {
		File file = new File();
		file.setContentType(multipartFile.getContentType());
		file.setName(multipartFile.getOriginalFilename());
		file.setSize(Long.toString(multipartFile.getSize()));
		file.setUserId(userId);

		try {
			file.setData(multipartFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
		
		return fileMapper.insertFile(file);
	}
	
	public List<File> getAllFilesForUser(User user) {
		return fileMapper.getAllFilesForUser(user.getId());
	}

	public File getFileById(int id) {
		return fileMapper.getFileById(id);
	}

	public int deleteFileById(int id) {
		return fileMapper.deleteFileById(id);
	}
}
