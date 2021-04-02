package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;

@Controller
@RequestMapping("file")
public class FileController {
	@Autowired
	private FileService fileService;

	@PostMapping
	public String uploadFile(MultipartFile file, int userId, RedirectAttributes ra) {
		if (fileService.uploadFile(file, userId) == 1) {
			ra.addFlashAttribute("message", "Successfully uploaded file");
		} else {
			ra.addFlashAttribute("error", "An error occurred while uploading the file");
		}

		return "redirect:/home";
	}
	
	@GetMapping("view/{id}")
	public ResponseEntity<Resource> viewFile(@PathVariable int id) {
		File file = fileService.getFileById(id);
		Resource resource = new ByteArrayResource(file.getData(), file.getName());

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
				.body(resource);
	}

	@GetMapping("delete/{id}")
	public String deleteFile(@PathVariable int id, RedirectAttributes ra) {
		if (fileService.deleteFileById(id) == 1) {
			ra.addFlashAttribute("message", "Successfully deleted file");
		} else {
			ra.addFlashAttribute("error", "An error occurred while deleting the file");
		}

		return "redirect:/home";
	}
}
