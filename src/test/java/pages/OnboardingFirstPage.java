package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class OnboardingFirstPage {

	private AndroidDriver driver;
	private WebDriverWait wait;

	public OnboardingFirstPage(AndroidDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
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