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

		registerPage.register("Test Kullanıcı", email, "5554443322", "Test123");

		registerPage.acceptAgreements();

		registerPage.clickRegister();

		By loginHeader = By.xpath("//android.widget.TextView[@text='Giriş Yap']");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeader));

		Assert.assertTrue(driver.findElement(loginHeader).isDisplayed(), "Redirection to login screen failed!");
		System.out.println("Register successful, redirected to Login screen.");

		System.out.println("Register flow completed successfully.");
	}
}