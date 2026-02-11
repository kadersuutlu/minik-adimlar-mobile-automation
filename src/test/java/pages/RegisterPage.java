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

	private By kvkkCheckbox = AppiumBy.accessibilityId("signup_kvkk_checkbox");
	private By kvkkPage = AppiumBy.accessibilityId("Okudum, Onaylıyorum");
	
	private By userAgreementCheckbox = AppiumBy.accessibilityId("signup_user_agreement_checkbox");
	private By userAgreementPage = AppiumBy.accessibilityId("Okudum, Onaylıyorum");
	
	private By privacyCheckbox = AppiumBy.accessibilityId("signup_privacy_policy_checkbox");
	private By privacyPage = AppiumBy.accessibilityId("Okudum, Onaylıyorum");

	private By registerButton = AppiumBy.accessibilityId("signup_continue_button");

	private By emailError = AppiumBy.accessibilityId("signup_email_input_error");
    private By passwordError = AppiumBy.accessibilityId("signup_password_input_error");
	
	private By goToSignInButton = AppiumBy.accessibilityId("signup_go_to_signin_button");
	
	public boolean isDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(registerHeader)).isDisplayed();
    }

    public void enterName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
    }

    public void enterPhone(String phone) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneField)).sendKeys(phone);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    public void acceptAgreements() {
        wait.until(ExpectedConditions.elementToBeClickable(kvkkCheckbox)).click();
        wait.until(ExpectedConditions.elementToBeClickable(userAgreementCheckbox)).click();
        wait.until(ExpectedConditions.elementToBeClickable(privacyCheckbox)).click();
    }

    public void clickRegister() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    public String getEmailErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailError)).getText();
    }

    public String getPasswordErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError)).getText();
    }

    public boolean isRegisterButtonEnabled() {
        return driver.findElement(registerButton).isEnabled();
    }
    
    public void fillRegisterForm(String name, String email, String phone, String password) {
        enterName(name);
        enterEmail(email);
        enterPhone(phone);
        enterPassword(password);
    }

}