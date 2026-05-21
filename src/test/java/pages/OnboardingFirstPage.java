package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class OnboardingFirstPage extends BasePage{
	
	public OnboardingFirstPage(AndroidDriver driver) {
		super(driver);
		System.out.println("Onboarding 1 initialized");
	}

	private By continueButton = AppiumBy.accessibilityId("welcome_continue_button");

	public void tapContinue() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(continueButton));
		driver.findElement(continueButton).click();
	}

	public boolean isContinueButtonDisplayed() {
		return driver.findElement(continueButton).isDisplayed();
	}
}