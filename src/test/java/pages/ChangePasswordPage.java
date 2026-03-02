package pages;

import org.openqa.selenium.By;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class ChangePasswordPage extends BasePage {

	public ChangePasswordPage(AndroidDriver driver) {
		super(driver);
		System.out.println("ChangePasswordPage initialized");
	}

	By changePasswordTitle = AppiumBy.accessibilityId("change_password_title");

	public boolean isDisplayed() {
		return driver.findElements(changePasswordTitle).size() > 0;
	}
}