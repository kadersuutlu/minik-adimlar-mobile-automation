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
import pages.LoginPage;

public class LoginTest extends BaseTest {

	private LoginPage loginPage;

	@BeforeEach
	public void setupPage() {
		AppFlowManager flow = new AppFlowManager(driver);
		flow.goToLogin();
		loginPage = new LoginPage(driver);
	}

	@Test
	public void successfulLoginRedirectsToFirstAddBabyPage() {

		loginPage.fillLoginForm("validuser@gmail.com", "Valid123");
		loginPage.clickLogin();

		By firstAddBabyHeader = AppiumBy.accessibilityId("add_baby_title");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(firstAddBabyHeader));

		Assert.assertTrue(driver.findElement(firstAddBabyHeader).isDisplayed(), "Login did not redirect to Home page");
	}

	@Test
	public void loginWithInvalidEmailShowsError() {

		loginPage.fillLoginForm("invalidemail", "Valid123");

		Assert.assertTrue(loginPage.getEmailErrorText().length() > 0, "Email format error should be displayed");
	}

	@Test
	public void loginWithEmptyPasswordShowsError() {

		loginPage.fillLoginForm("test@gmail.com", "");

		Assert.assertTrue(loginPage.getPasswordErrorText().length() > 0, "Password required error should be displayed");
	}

	@Test
	public void loginButtonDisabledWhenFieldsAreInvalid() {

		loginPage.fillLoginForm("invalidemail", "");

		Assert.assertFalse(loginPage.isLoginButtonEnabled(), "Login button should be disabled when inputs are invalid");
	}

	@Test
	public void loginWithWrongPasswordShowsError() {

		loginPage.fillLoginForm("validuser@gmail.com", "Wrong123");
		loginPage.clickLogin();

		By authError = AppiumBy.accessibilityId("signin_general_error");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(authError));

		Assert.assertTrue(driver.findElement(authError).isDisplayed(),
				"Wrong password error message should be displayed");
	}
}