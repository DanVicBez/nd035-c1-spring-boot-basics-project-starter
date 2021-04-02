package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.*;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {
	@LocalServerPort
	private int port;

	private WebDriver driver;
	
	private String url;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
		url = "http://localhost:" + port + "/";
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}
	
	@Test
	public void unauthorizedUserCanOnlyAccessLoginAndSignup() {
		driver.get(url + "login");
		assertEquals("Login", driver.getTitle());
		
		driver.get(url + "signup");
		assertEquals("Sign Up", driver.getTitle());
		
		driver.get(url + "home");
		assertEquals("Login", driver.getTitle());
		
		driver.get(url + "invalid-url");
		assertEquals("Login", driver.getTitle());
	}
	
	@Test
	public void testSignUpLogInAndLogOut() {
		signUpAndLogIn();
		assertEquals("Home", driver.getTitle());
		
		// log out and verify home now redirects to login
		HomePage home = new HomePage(driver);
		home.logOut();
		driver.get(url + "home");
		assertEquals("Login", driver.getTitle());
	}
	
	@Test
	public void testNoteCreateReadUpdateDelete() {
		signUpAndLogIn();
		HomePage home = new HomePage(driver);
	
		// add note and verify it is displayed
		home.openNotesTab();
		home.addNote("weather", "30 degrees");
		home.openNotesTab();
		assertEquals("weather", home.getFirstNoteTitle());
		assertEquals("30 degrees", home.getFirstNoteDescription());
		
		// edit note and verify info has been updated
		home.editFirstNote("weather for tomorrow", "48 degrees, rainy");
		home.openNotesTab();
		assertEquals("weather for tomorrow", home.getFirstNoteTitle());
		assertEquals("48 degrees, rainy", home.getFirstNoteDescription());
		
		// delete note and verify it is no longer there
		// may take a while due to timeouts
		home.deleteFirstNote();
		home.openNotesTab();
		assertThrows(TimeoutException.class, home::getFirstNoteTitle);
	}
	
	@Test
	public void testCredentialCreateReadUpdateDelete() throws InterruptedException {
		signUpAndLogIn();
		HomePage home = new HomePage(driver);
		
		// add credential and verify it is displayed and not showing decrypted password
		home.openCredentialsTab();
		home.addCredentials("udacity.com", "jsmith", "java_rocks$42");
		home.openCredentialsTab();
		assertEquals("udacity.com", home.getFirstCredentialUrl());
		assertEquals("jsmith", home.getFirstCredentialUsername());
		assertNotEquals("java_rocks$42", home.getFirstCredentialPassword());
		
		// edit credential, verify decrypted password is visible and info has been updated
		assertEquals("java_rocks$42", home.editFirstCredential("www.udacity.com", "jsmith@aol.com", "j@v@_r0cks$43"));
		home.openCredentialsTab();
		assertEquals("www.udacity.com", home.getFirstCredentialUrl());
		assertEquals("jsmith@aol.com", home.getFirstCredentialUsername());
		assertNotEquals("j@v@_r0cks$43", home.getFirstCredentialPassword());

		// delete note and verify it is no longer there
		// may take a while due to timeouts
		home.deleteFirstCredential();
		home.openCredentialsTab();
		assertThrows(TimeoutException.class, home::getFirstCredentialUrl);
	}

	private void signUpAndLogIn() {
		// sign up
		driver.get(url + "signup");
		SignupPage signup = new SignupPage(driver);
		signup.register("John", "Smith", "jsmith84", "udacity123");
		
		// log in
		driver.get(url + "login");
		LoginPage login = new LoginPage(driver);
		login.logIn("jsmith84", "udacity123");
	}
}
