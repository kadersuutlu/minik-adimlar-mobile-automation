package pages;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class RegisterPage {

	private AppiumDriver driver;

	public RegisterPage(AppiumDriver driver) {
		this.driver = driver;
	}

	private By nameField = By.xpath("//android.widget.EditText[@text='Ad ve Soyadınızı Girin']");

	private By emailField = By.xpath("//android.widget.EditText[@text='E-Mail Adresinizi Girin']");

	private By phoneField = By.xpath("//android.widget.EditText[@text='+90 (5__) ___ __ __']");

	private By passwordField = By.xpath("//android.widget.EditText[@text=\"Şifrenizi Belirleyin\"]");

	private By kvkkCheckbox = AppiumBy.accessibilityId("KVKK metnini okudum, onaylıyorum.*");

	private By userAgreementCheckbox = AppiumBy.accessibilityId("Kullanıcı Sözleşmesi'ni okudum, onaylıyorum.*");

	private By privacyAgreementCheckbox = AppiumBy.accessibilityId("Gizlilik Sözleşmesi'ni okudum, onaylıyorum.*");

	private By registerTitle = By.xpath("//android.widget.TextView[@text='Kayıt Ol']");

	public boolean isDisplayed() {
		return driver.findElement(registerTitle).isDisplayed();
	}

	private By continueButton = AppiumBy.accessibilityId("Devam Et");

	public void enterName(String name) {
		driver.findElement(nameField).sendKeys(name);
	}

	public void enterEmail(String email) {
		driver.findElement(emailField).sendKeys(email);
	}

	public void enterPhone(String phone) {
		driver.findElement(phoneField).sendKeys(phone);
	}

	public void enterPassword(String password) {
		driver.findElement(passwordField).sendKeys(password);
	}

	public void acceptAgreements() {
		driver.findElement(kvkkCheckbox).click();
		driver.findElement(userAgreementCheckbox).click();
		driver.findElement(privacyAgreementCheckbox).click();
	}

	public void tapContinue() {
		driver.findElement(continueButton).click();
	}

	public void register(String name, String email, String phone, String password) {
		enterName(name);
		enterEmail(email);
		enterPhone(phone);
		enterPassword(password);
		acceptAgreements();
		tapContinue();
	}

}