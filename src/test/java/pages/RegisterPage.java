package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;

public class RegisterPage {

	private AppiumDriver driver;
	private WebDriverWait wait;

	public RegisterPage(AppiumDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	private By nameField = By.xpath("//android.widget.EditText[@text='Ad ve Soyadınızı Girin']");
	private By emailField = By.xpath("//android.widget.EditText[@text='E-Mail Adresinizi Girin']");
	private By phoneField = By.xpath("//android.widget.EditText[@text='+90 (5__) ___ __ __']");
	private By passwordField = By.xpath("//android.widget.EditText[@text='Şifrenizi Belirleyin']");

	private By kvkkCheckbox = By.xpath("//android.view.ViewGroup[contains(@content-desc, 'KVKK')]");
	private By userAgreementCheckbox = By
			.xpath("//android.view.ViewGroup[contains(@content-desc, 'Kullanıcı Sözleşmesi')]");
	private By privacyCheckbox = By.xpath("//android.view.ViewGroup[contains(@content-desc, 'Gizlilik Sözleşmesi')]");

	private By registerButton = By.xpath("//android.widget.TextView[@text='Devam Et']");

	public void register(String name, String email, String phone, String password) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
		System.out.println("Name entered");

		wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
		System.out.println("Email entered");

		wait.until(ExpectedConditions.visibilityOfElementLocated(phoneField)).sendKeys(phone);
		System.out.println("Phone entered");

		wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
		System.out.println("Password entered");
	}

	public void acceptAgreements() {
		wait.until(ExpectedConditions.elementToBeClickable(kvkkCheckbox)).click();
		System.out.println("KVKK accepted");

		wait.until(ExpectedConditions.elementToBeClickable(userAgreementCheckbox)).click();
		System.out.println("User Agreement accepted");

		wait.until(ExpectedConditions.elementToBeClickable(privacyCheckbox)).click();
		System.out.println("Privacy accepted");
	}

	public void clickRegister() {
		wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
		System.out.println("Register button clicked");
	}

}