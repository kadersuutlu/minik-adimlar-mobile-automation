package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AddBabyPage {
	private AndroidDriver driver;
	private WebDriverWait wait;

	public AddBabyPage(AndroidDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		System.out.println("AddBabyPage initialized");
	}

	By baby_modal_title = AppiumBy.accessibilityId("baby_modal_title");
	
	public boolean isDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(baby_modal_title)).isDisplayed();
	}
}
