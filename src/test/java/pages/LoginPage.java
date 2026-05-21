package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class LoginPage extends BasePage{

	public LoginPage(AndroidDriver driver) {
		super(driver);
		System.out.println("Giriş Yap initialized");
	}

	By loginHeader = AppiumBy.accessibilityId("signin_title");

	private By emailField = AppiumBy.accessibilityId("signin_email_input");
	private By passwordField = AppiumBy.accessibilityId("signin_password_input");

	private By passwordVisibilityIcon = AppiumBy
			.xpath("//*[contains(@resource-id,'signin_password_input_visibility_icon')]");

	private By loginButton = AppiumBy.accessibilityId("signin_continue_button");

	private By forgotPasswordField = AppiumBy.accessibilityId("signin_forgot_password_text");

	private By emailError = By.xpath("//android.widget.TextView[@text='Bu e-mail adresi kayıtlı değil.']");
	private By passwordError = By.xpath("//android.widget.TextView[@text='Şifrenizi eksik ya da hatalı girdiniz.']");

	private By goToSignUpButton = AppiumBy.accessibilityId("signin_go_to_signup_button");

	public boolean isDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeader)).isDisplayed();
	}

	public void enterEmail(String email) {
		driver.findElement(emailField).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
	}

	public void enterPassword(String password) {
		driver.findElement(passwordField).click();
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

	public void clickEmailField() {
		driver.findElement(emailField).click();
	}

	public void clickForgotPassword() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(forgotPasswordField)).click();
	}

	public void clickPasswordVisibility() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(passwordVisibilityIcon)).click();
	}

	public String getPasswordText() {
		return driver.findElement(passwordField).getText();
	}

	public void clickGoToSignUpButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(goToSignUpButton)).click();
	}
}