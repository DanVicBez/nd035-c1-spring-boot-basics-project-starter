package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class FileExceptionHandler {
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public String handleError(RedirectAttributes ra) {
		ra.addFlashAttribute("error", "File exceeds maximum allowed upload size");
		return "redirect:/home";
	}
}
