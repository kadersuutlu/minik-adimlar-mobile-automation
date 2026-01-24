package pages;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;

public class OnboardingSecondPage {

	private AppiumDriver driver;

	public OnboardingSecondPage(AppiumDriver driver) {
		this.driver = driver;
	}

	private By registerButton = By.xpath("//android.widget.TextView[@text='Kayıt Ol']");

	By loginButton = By.xpath("//android.widget.TextView[@text='Giriş Yap']");

	public void tapRegister() {
		driver.findElement(registerButton).click();
	}

	public void tapLogin() {
		driver.findElement(loginButton).click();
	}

}
