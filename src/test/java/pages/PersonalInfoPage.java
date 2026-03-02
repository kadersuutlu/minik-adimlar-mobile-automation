package pages;

import org.openqa.selenium.By;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class PersonalInfoPage extends BasePage {

	public PersonalInfoPage(AndroidDriver driver) {
		super(driver);
		System.out.println("PersonalInfoPage initialized");
	}

	By personalInfoTitle = AppiumBy.accessibilityId("personal_info_title");

	public boolean isDisplayed() {
		return driver.findElements(personalInfoTitle).size() > 0;
	}
}