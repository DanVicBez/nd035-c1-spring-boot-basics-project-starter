package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;

@Controller
@RequestMapping("file")
public class FileController {
	@Autowired
	private FileService fileService;

	@PostMapping
	public String uploadFile(MultipartFile file, int userId) {
		fileService.uploadFile(file, userId);
		return "redirect:/home";
	}
}
