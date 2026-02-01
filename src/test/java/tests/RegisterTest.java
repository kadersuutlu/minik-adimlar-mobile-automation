package tests;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BaseTest;
import pages.RegisterPage;

public class RegisterTest extends BaseTest {

	@Test
	public void successfulRegisterRedirectsToLogin() {

		System.out.println("Register test started...");

		RegisterPage registerPage = new RegisterPage(driver);

		String email = "test" + System.currentTimeMillis() + "@gmail.com"; // Created unique email

		registerPage.enterName("Test Kullanıcı");
		registerPage.enterEmail(email);
		registerPage.enterPhone("5554443322");
		registerPage.enterPassword("Test123");

		registerPage.acceptAgreements();

		registerPage.clickRegister();

		By loginHeader = By.xpath("//android.widget.TextView[@text='Giriş Yap']");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeader));

		Assert.assertTrue(driver.findElement(loginHeader).isDisplayed(), "Redirection to login screen failed!");

		System.out.println("Register successful, redirected to Login screen.");

		System.out.println("Register flow completed successfully.");
	}

	@Test
	public void registerWithInvalidEmailShowsError() {

		System.out.println("Register test started...");

		RegisterPage registerPage = new RegisterPage(driver);

		registerPage.enterName("Test Kullanıcı");
		registerPage.enterEmail("test123");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(registerPage.getNameField())).click();

		registerPage.enterPhone("5554443322");
		registerPage.enterPassword("Test123");

		registerPage.acceptAgreements();

		Assert.assertTrue(registerPage.isEmailFormatErrorDisplayed(),
				"Invalid email format error message should be displayed");

		System.out.println("Register flow completed successfully.");

	}

	/*
	 * 1) Şifre zorunludur Beklenen hata: "Şifre zorunludur." Test input: "" // boş
	 * string
	 * 
	 * 2) Minimum uzunluk (en az 6 karakter) Beklenen hata:
	 * "Şifre en az 6 karakter olmalıdır." Test input: "Ab123" // 5 karakter
	 * 
	 * 3) En az 1 büyük harf içermelidir Beklenen hata:
	 * "Şifre en az 1 büyük harf içermelidir." Test input: "test123" // büyük harf
	 * yok
	 * 
	 * 4) En az 1 küçük harf içermelidir Beklenen hata:
	 * "Şifre en az 1 küçük harf içermelidir." Test input: "TEST123" // küçük harf
	 * yok
	 */

	@Test
	public void registerWithEmptyPasswordShowsRequiredError() {

		RegisterPage registerPage = new RegisterPage(driver);

		registerPage.enterName("Test Kullanıcı");
		registerPage.enterEmail("test@gmail.com");
		registerPage.enterPhone("5554443322");
		registerPage.enterPassword("");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(registerPage.getNameField())).click();

		registerPage.acceptAgreements();

		Assert.assertTrue(registerPage.isPasswordEmptyErrorDisplayed(), "Password required error should be displayed");
	}

	@Test
	public void registerWithShortPasswordShowsMinLengthErrorAndDisablesButton() {

		RegisterPage registerPage = new RegisterPage(driver);

		registerPage.enterName("Test Kullanıcı");
		registerPage.enterEmail("test@gmail.com");
		registerPage.enterPhone("5554443322");
		registerPage.enterPassword("Ab123");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(registerPage.getNameField())).click();

		registerPage.acceptAgreements();

		Assert.assertTrue(registerPage.isPasswordMinLengthErrorDisplayed(), "Min length error should be displayed");

		Assert.assertFalse(registerPage.isRegisterButtonEnabled(),
				"Register button should be disabled when password is too short");
	}

	@Test
	public void registerWithPasswordWithoutUppercaseShowsError() {

		RegisterPage registerPage = new RegisterPage(driver);

		registerPage.enterName("Test Kullanıcı");
		registerPage.enterEmail("test@gmail.com");
		registerPage.enterPhone("5554443322");
		registerPage.enterPassword("test123");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(registerPage.getNameField())).click();

		registerPage.acceptAgreements();

		Assert.assertTrue(registerPage.isPasswordUppercaseErrorDisplayed(),
				"Uppercase letter error should be displayed");
	}

	@Test
	public void registerWithPasswordWithoutLowercaseShowsError() {

		RegisterPage registerPage = new RegisterPage(driver);

		registerPage.enterName("Test Kullanıcı");
		registerPage.enterEmail("test@gmail.com");
		registerPage.enterPhone("5554443322");
		registerPage.enterPassword("TEST123");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(registerPage.getNameField())).click();

		registerPage.acceptAgreements();

		Assert.assertTrue(registerPage.isPasswordLowercaseErrorDisplayed(),
				"Lowercase letter error should be displayed");
	}

}