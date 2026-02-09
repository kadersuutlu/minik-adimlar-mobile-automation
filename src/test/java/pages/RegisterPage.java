package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class RegisterPage {

	private AndroidDriver driver;
	private WebDriverWait wait;

	public RegisterPage(AndroidDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		System.out.println("RegisterPage initialized");
	}

	By registerHeader = AppiumBy.accessibilityId("signup_title");
	
	private By nameField = AppiumBy.accessibilityId("signup_name_input");
	private By emailField = AppiumBy.accessibilityId("signup_email_input");
	private By phoneField = AppiumBy.accessibilityId("signup_phone_input");
	private By passwordField = AppiumBy.accessibilityId("signup_password_input");
	
	private By passwordVisibilityIcon = AppiumBy.accessibilityId("signup_password_input_visibility_icon");

	private By kvkkCheckbox = AppiumBy.accessibilityId("signup_name_input");
	private By userAgreementCheckbox = AppiumBy.accessibilityId("signup_name_input");
	private By privacyCheckbox = AppiumBy.accessibilityId("signup_name_input");

	private By registerButton = AppiumBy.accessibilityId("signup_continue_button");

	private By emailFormatError = AppiumBy.accessibilityId("signup_email_input_error");

	private By passwordMinLengthError = AppiumBy.accessibilityId("signup_password_input_error");

	private By passwordUppercaseError = AppiumBy.accessibilityId("signup_password_input_error");

	private By passwordLowercaseError = AppiumBy.accessibilityId("signup_password_input_error");

	private By passwordEmptyError = AppiumBy.accessibilityId("signup_password_input_error");
	
	private By goToSignInButton = AppiumBy.accessibilityId("signup_go_to_signin_button");
	
	public boolean isDisplayed() {
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(registerHeader)).isDisplayed();
	}

	public void enterName(String name) {
		System.out.println("Entering name: " + name);
		wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
		System.out.println("Name entered and blur triggered");
	}

	public void enterEmail(String email) {
		System.out.println("Entering email: " + email);
		wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
		System.out.println("Email entered and blur triggered");
	}

	public void enterPhone(String phone) {
		System.out.println("Entering phone: " + phone);
		wait.until(ExpectedConditions.visibilityOfElementLocated(phoneField)).sendKeys(phone);
		System.out.println("Phone entered and blur triggered");
	}

	public void enterPassword(String password) {
		System.out.println("Entering password: [" + password + "]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
		System.out.println("Password entered and blur triggered");
	}

	public void acceptAgreements() {
		System.out.println("Accepting KVKK");
		wait.until(ExpectedConditions.elementToBeClickable(kvkkCheckbox)).click();

		System.out.println("Accepting User Agreement");
		wait.until(ExpectedConditions.elementToBeClickable(userAgreementCheckbox)).click();

		System.out.println("Accepting Privacy Agreement");
		wait.until(ExpectedConditions.elementToBeClickable(privacyCheckbox)).click();

		System.out.println("All agreements accepted");
	}

	public void clickRegister() {
		System.out.println("Clicking Register button");
		wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
	}

	public boolean isRegisterButtonEnabled() {
		boolean enabled = driver.findElement(registerButton).isEnabled();
		System.out.println("Register button enabled: " + enabled);
		return enabled;
	}

	public boolean isEmailFormatErrorDisplayed() {
		boolean displayed = driver.findElement(emailFormatError).isDisplayed();
		System.out.println("Email format error displayed: " + displayed);
		return displayed;
	}

	public boolean isPasswordMinLengthErrorDisplayed() {
		boolean displayed = driver.findElement(passwordMinLengthError).isDisplayed();
		System.out.println("Password min length error displayed: " + displayed);
		return displayed;
	}

	public boolean isPasswordUppercaseErrorDisplayed() {
		boolean displayed = driver.findElement(passwordUppercaseError).isDisplayed();
		System.out.println("Password uppercase error displayed: " + displayed);
		return displayed;
	}

	public boolean isPasswordLowercaseErrorDisplayed() {
		boolean displayed = driver.findElement(passwordLowercaseError).isDisplayed();
		System.out.println("Password lowercase error displayed: " + displayed);
		return displayed;
	}

	public boolean isPasswordEmptyErrorDisplayed() {
		boolean displayed = driver.findElement(passwordEmptyError).isDisplayed();
		System.out.println("Password empty error displayed: " + displayed);
		return displayed;
	}
}