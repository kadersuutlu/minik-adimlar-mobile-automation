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
	}

	By loginHeader = AppiumBy.accessibilityId("signin_title");

	private By emailField = AppiumBy.accessibilityId("signin_email_input");
	private By passwordField = AppiumBy.accessibilityId("signin_password_input");

	private By passwordVisibilityIcon = AppiumBy.accessibilityId("signin_password_input_visibility_icon");

	private By loginButton = AppiumBy.accessibilityId("signin_continue_button");

	private By forgotPasswordField = AppiumBy.accessibilityId("signin_title");

	private By emailFormatError = AppiumBy.accessibilityId("signin_forgot_password_text");

	private By goToSignUpButton = AppiumBy.accessibilityId("signin_go_to_signup_button");

	public boolean isDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeader)).isDisplayed();
	}

	public void enterEmail(String email) {
		System.out.println("Entering email: " + email);
		wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
		System.out.println("Email entered and blur triggered");
	}

	public void enterPassword(String password) {
		System.out.println("Entering password: [" + password + "]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
		System.out.println("Password entered and blur triggered");
	}

	public void clickLogin() {
		System.out.println("Clicking Login button");
		wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
	}

	public boolean isLoginButtonEnabled() {
		boolean enabled = driver.findElement(loginButton).isEnabled();
		System.out.println("Login button enabled: " + enabled);
		return enabled;
	}
}