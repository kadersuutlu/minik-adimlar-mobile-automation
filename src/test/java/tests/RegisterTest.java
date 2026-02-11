package tests;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.AppFlowManager;
import base.BaseTest;
import io.appium.java_client.AppiumBy;
import pages.RegisterPage;

public class RegisterTest extends BaseTest {

	private RegisterPage registerPage;

	@BeforeEach
	public void setupPage() {
		AppFlowManager flow = new AppFlowManager(driver);
	    flow.goToRegister();
		registerPage = new RegisterPage(driver);
	}

	@Test
	public void successfulRegisterRedirectsToLogin() {

		String email = "test" + System.currentTimeMillis() + "@gmail.com";

		registerPage.fillRegisterForm("Test Kullanıcı", email, "5554443322", "Test123");

		registerPage.acceptAgreements();
		registerPage.clickRegister();

		By loginTitle = AppiumBy.accessibilityId("signin_title");

		new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.visibilityOfElementLocated(loginTitle));

		Assert.assertTrue(driver.findElement(loginTitle).isDisplayed(),
				"User should be redirected to Login page after successful registration");
	}

	@Test
	public void registerWithInvalidEmailShowsError() {

		registerPage.fillRegisterForm("Test Kullanıcı", "invalidEmail", "5554443322", "Test123");

		registerPage.acceptAgreements();

		String errorText = registerPage.getEmailErrorText();

		Assert.assertTrue(errorText.contains("geçerli"), "Invalid email error message should be displayed");
	}
	
	
	//1) Şifre zorunludur Beklenen hata: "Şifre zorunludur." Test input: "" // boş string

	@Test
	public void registerWithEmptyPasswordShowsError() {

		registerPage.fillRegisterForm("Test Kullanıcı", "test@gmail.com", "5554443322", "");

		registerPage.acceptAgreements();

		String errorText = registerPage.getPasswordErrorText();

		Assert.assertTrue(errorText.contains("zorunlu"), "Password required error should be displayed");
	}
	
	//2) Minimum uzunluk (en az 6 karakter) Beklenen hata: "Şifre en az 6 karakter olmalıdır." Test input: "Ab123" // 5 karakter

	@Test
	public void registerWithShortPasswordShowsMinLengthError() {

		registerPage.fillRegisterForm("Test Kullanıcı", "test@gmail.com", "5554443322", "Ab123");

		registerPage.acceptAgreements();

		String errorText = registerPage.getPasswordErrorText();

		Assert.assertTrue(errorText.contains("en az"), "Min length password error should be displayed");

		Assert.assertFalse(registerPage.isRegisterButtonEnabled(),
				"Register button should be disabled for invalid password");
	}

	//3) En az 1 büyük harf içermelidir Beklenen hata: "Şifre en az 1 büyük harf içermelidir." Test input: "test123" // büyük harf yok
	
	@Test
	public void registerWithPasswordWithoutUppercaseShowsError() {

		registerPage.fillRegisterForm("Test Kullanıcı", "test@gmail.com", "5554443322", "test123");

		registerPage.acceptAgreements();

		String errorText = registerPage.getPasswordErrorText();

		Assert.assertTrue(errorText.contains("büyük harf"), "Uppercase letter error should be displayed");
	}
	
	//4) En az 1 küçük harf içermelidir Beklenen hata: "Şifre en az 1 küçük harf içermelidir." Test input: "TEST123" // küçük harf yok
	
	@Test
	public void registerWithPasswordWithoutLowercaseShowsError() {

		registerPage.fillRegisterForm("Test Kullanıcı", "test@gmail.com", "5554443322", "TEST123");

		registerPage.acceptAgreements();

		String errorText = registerPage.getPasswordErrorText();

		Assert.assertTrue(errorText.contains("büyük harf"), "Uppercase letter error should be displayed");
	}
}