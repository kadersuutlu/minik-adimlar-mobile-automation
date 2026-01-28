package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class OnboardingFirstPage {

	private AppiumDriver driver;

	public OnboardingFirstPage(AppiumDriver driver) {
		this.driver = driver;
	}

	private By continueButton = By.xpath("//android.widget.TextView[@text='Devam Et']");

	public void tapContinue() {
		driver.findElement(continueButton).click();
	}

}