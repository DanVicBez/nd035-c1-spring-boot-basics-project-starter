package com.udacity.jwdnd.course1.cloudstorage;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	@FindBy(css = "#logoutDiv button")
	private WebElement logoutButton;
	
	@FindBy(css = "#nav-notes > button")
	private WebElement addNoteButton;
	
	@FindBy(css = "#nav-notes-tab")
	private WebElement notesTab;
	
	@FindBy(id = "note-title")
	private WebElement noteTitleInput;
	
	@FindBy(id = "note-description")
	private WebElement noteDescriptionInput;
	
	@FindBy(css = "#noteTable tbody th")
	private WebElement firstNoteTitle;
	
	@FindBy(css = "#noteTable th + td")
	private WebElement firstNoteDescription;
	
	@FindBy(css = "#noteTable button")
	private WebElement firstNoteEditButton;
	
	@FindBy(css = "#noteTable a")
	private WebElement firstNoteDeleteButton;
	
	@FindBy(css = "#nav-credentials > button")
	private WebElement addCredentialButton;
	
	@FindBy(css = "#nav-credentials-tab")
	private WebElement credentialsTab;
	
	@FindBy(id = "credential-url")
	private WebElement credentialUrlInput;
	
	@FindBy(id = "credential-username")
	private WebElement credentialUsernameInput;
	
	@FindBy(id = "credential-password")
	private WebElement credentialPasswordInput;
	
	@FindBy(css = "#credentialTable tbody th")
	private WebElement firstCredentialUrl;
	
	@FindBy(css = "#credentialTable th + td")
	private WebElement firstCredentialUsername;
	
	@FindBy(css = "#credentialTable td + td")
	private WebElement firstCredentialPassword;
	
	@FindBy(css = "#credentialTable button")
	private WebElement firstCredentialEditButton;
	
	@FindBy(css = "#credentialTable a")
	private WebElement firstCredentialDeleteButton;

	private WebDriverWait wait;
	
	public HomePage(WebDriver driver) {
		wait = new WebDriverWait(driver, 3);
		PageFactory.initElements(driver, this);
	}
	
	public void logOut() {
		logoutButton.submit();
	}
	
	public void openNotesTab() {
		// I was unable to find a better way to ensure that the link isn't clicked too soon
		try {
			Thread.sleep(2000);
		} catch(InterruptedException e) {}

		notesTab.click();
	}

	public void addNote(String title, String description) {
		addNoteButton.click();
		
		noteTitleInput.sendKeys(title);
		noteDescriptionInput.sendKeys(description);
		
		noteTitleInput.submit();
	}
	
	public String getFirstNoteTitle() {
		wait.until(visibilityOfAllElements(firstNoteTitle));
		return firstNoteTitle.getText();
	}
	
	public String getFirstNoteDescription() {
		wait.until(visibilityOfAllElements(firstNoteDescription));
		return firstNoteDescription.getText();
	}

	public void editFirstNote(String title, String description) {
		firstNoteEditButton.click();

		noteTitleInput.clear();
		noteTitleInput.sendKeys(title);

		noteDescriptionInput.clear();
		noteDescriptionInput.sendKeys(description);
		
		noteTitleInput.submit();
	}

	public void deleteFirstNote() {
		firstNoteDeleteButton.click();
	}

	public void openCredentialsTab() {
		// I was unable to find a better way to ensure that the link isn't clicked too soon
		try {
			Thread.sleep(2000);
		} catch(InterruptedException e) {}

		credentialsTab.click();
	}

	public void addCredentials(String url, String username, String password) {
		addCredentialButton.click();
		
		credentialUrlInput.sendKeys(url);
		credentialUsernameInput.sendKeys(username);
		credentialPasswordInput.sendKeys(password);
		
		credentialUrlInput.submit();
	}
	
	public String getFirstCredentialUrl() {
		wait.until(visibilityOfAllElements(firstCredentialUrl));
		return firstCredentialUrl.getText();
	}
	
	public String getFirstCredentialUsername() {
		wait.until(visibilityOfAllElements(firstCredentialUsername));
		return firstCredentialUsername.getText();
	}
	
	public String getFirstCredentialPassword() {
		wait.until(visibilityOfAllElements(firstCredentialPassword));
		return firstCredentialPassword.getText();
	}

	public String editFirstCredential(String url, String username, String password) {
		firstCredentialEditButton.click();

		credentialUrlInput.clear();
		credentialUrlInput.sendKeys(url);

		credentialUsernameInput.clear();
		credentialUsernameInput.sendKeys(username);

		String decryptedPassword = credentialPasswordInput.getAttribute("value");
		credentialPasswordInput.clear();
		credentialPasswordInput.sendKeys(password);
		
		credentialUrlInput.submit();
		return decryptedPassword;
	}

	public void deleteFirstCredential() {
		firstCredentialDeleteButton.click();
	}
}
