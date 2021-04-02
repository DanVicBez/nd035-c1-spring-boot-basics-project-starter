package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
	@FindBy(id = "inputFirstName")
	private WebElement inputFirstName;

	@FindBy(id = "inputLastName")
	private WebElement inputLastName;

	@FindBy(id = "inputUsername")
	private WebElement inputUsername;

	@FindBy(id = "inputPassword")
	private WebElement inputPassword;
	
	@FindBy(id = "login-link")
	private WebElement loginLink;
	
	public SignupPage(WebDriver webDriver) {
		PageFactory.initElements(webDriver, this);
	}
	
	public void register(String firstName, String lastName, String username, String password) {
		inputFirstName.sendKeys(firstName);
		inputLastName.sendKeys(lastName);
		inputUsername.sendKeys(username);
		inputPassword.sendKeys(password);
		
		inputFirstName.submit();
	}
	
	public void goToLogin() {
		loginLink.click();
	}
}
