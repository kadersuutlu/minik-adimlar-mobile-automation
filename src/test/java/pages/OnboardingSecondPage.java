package pages;

import org.openqa.selenium.By;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class OnboardingSecondPage extends BasePage{

	public OnboardingSecondPage(AndroidDriver driver) {
		super(driver);
	}

	private By registerButton = AppiumBy.accessibilityId("auth_choice_signup_button");
	private By loginButton = AppiumBy.accessibilityId("auth_choice_signin_button");
	private By welcomeToApp = By.xpath("//android.widget.TextView[@text=\"Bebeğiniz büyürken, biz de sizin yol arkadaşınız olalım\"]");

	public boolean isWelcomeToAppDisplayed() {
		return driver.findElement(registerButton).isDisplayed();
	}
	
	public boolean isRegisterButtonDisplayed() {
		return driver.findElement(registerButton).isDisplayed();
	}

	public boolean isLoginButtonDisplayed() {
		return driver.findElement(loginButton).isDisplayed();
	}

	public void tapRegister() {
		driver.findElement(registerButton).click();
	}

	public void tapLogin() {
		driver.findElement(loginButton).click();
	}

}