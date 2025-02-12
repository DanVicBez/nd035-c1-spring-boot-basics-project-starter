package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	@FindBy(id = "inputUsername")
	private WebElement inputUsername;

	@FindBy(id = "inputPassword")
	private WebElement inputPassword;

	public LoginPage(WebDriver webDriver) {
		PageFactory.initElements(webDriver, this);
	}
	
	public void logIn(String username, String password) {
		inputUsername.sendKeys(username);
		inputPassword.sendKeys(password);
		
		inputUsername.submit();
	}
}
