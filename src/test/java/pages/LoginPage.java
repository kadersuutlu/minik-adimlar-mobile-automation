package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class LoginPage {
	private AndroidDriver driver;
	private WebDriverWait wait;

	public LoginPage(AndroidDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		System.out.println("Login initialized");
	}

	By loginHeader = AppiumBy.accessibilityId("signin_title");

	private By emailField = AppiumBy.accessibilityId("signin_email_input");
	private By passwordField = AppiumBy.accessibilityId("signin_password_input");

	private By passwordVisibilityIcon = AppiumBy.accessibilityId("signin_password_input_visibility_icon");

	private By loginButton = AppiumBy.accessibilityId("signin_continue_button");

	private By forgotPasswordField = AppiumBy.accessibilityId("signin_forgot_password_text");

	private By emailError = AppiumBy.accessibilityId("signin_email_input_error");
	private By passwordError = AppiumBy.accessibilityId("signup_password_input_error");

	private By goToSignUpButton = AppiumBy.accessibilityId("signin_go_to_signup_button");

	public boolean isDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeader)).isDisplayed();
	}

	public void enterEmail(String email) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
	}

	public void enterPassword(String password) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
	}

	public void clickLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
	}

	public String getEmailErrorText() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(emailError)).getText();
	}

	public String getPasswordErrorText() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError)).getText();
	}

	public boolean isLoginButtonEnabled() {
		return driver.findElement(loginButton).isEnabled();
	}

	public void fillLoginForm(String email, String password) {
		enterEmail(email);
		enterPassword(password);
	}
}