package pages;

import org.openqa.selenium.By;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OnboardingSecondPage extends BasePage{

	public OnboardingSecondPage(AndroidDriver driver) {
		super(driver);
		System.out.println("Onboarding 2 initialized");
	}

	private By registerButton = AppiumBy.accessibilityId("auth_choice_signup_button");
	private By loginButton = AppiumBy.accessibilityId("auth_choice_signin_button");

    public boolean isRegisterButtonDisplayed() {
        return isDisplayed(registerButton);
    }

    public boolean isLoginButtonDisplayed() {
        return isDisplayed(loginButton);
    }

    public void tapRegister() {
        click(registerButton);
    }

    public void tapLogin() {
        click(loginButton);
    }

}