package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class RegisterPage {

	private AndroidDriver driver;
	private WebDriverWait wait;

	public RegisterPage(AndroidDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		System.out.println("RegisterPage initialized");
	}

	private By nameField = By.xpath("//android.widget.EditText[@text='Ad ve Soyadınızı Girin']");
	
	public By getNameField() {
	    return nameField;
	}
	
	private By emailField = By.xpath("//android.widget.EditText[@text='E-Mail Adresinizi Girin']");
	private By phoneField = By.xpath("//android.widget.EditText[@text='+90 (5__) ___ __ __']");
	private By passwordField = By.xpath("//android.widget.EditText[@text='Şifrenizi Belirleyin']");

	private By kvkkCheckbox = By.xpath("//android.view.ViewGroup[contains(@content-desc, 'KVKK')]");
	private By userAgreementCheckbox = By
			.xpath("//android.view.ViewGroup[contains(@content-desc, 'Kullanıcı Sözleşmesi')]");
	private By privacyCheckbox = By.xpath("//android.view.ViewGroup[contains(@content-desc, 'Gizlilik Sözleşmesi')]");

	private By registerButton = By.xpath("//android.widget.TextView[@text='Devam Et']");

	private By emailFormatError = By.xpath("//android.widget.TextView[@text='Lütfen geçerli bir email adresi girin.']");

	private By passwordMinLengthError = By
			.xpath("//android.widget.TextView[@text='Şifre en az 6 karakter olmalıdır.']");

	private By passwordUppercaseError = By
			.xpath("//android.widget.TextView[@text='Şifre en az 1 büyük harf içermelidir.']");

	private By passwordLowercaseError = By
			.xpath("//android.widget.TextView[@text='Şifre en az 1 küçük harf içermelidir.']");

	private By passwordEmptyError = By.xpath("//android.widget.TextView[@text='Şifre zorunludur.']");

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