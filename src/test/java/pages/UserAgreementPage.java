package pages;

import org.openqa.selenium.By;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class UserAgreementPage extends BasePage {

	public UserAgreementPage(AndroidDriver driver) {
		super(driver);
		System.out.println("UserAgreementPage initialized");
	}

	By userAgreementPage = AppiumBy.accessibilityId("user_agreement_title");

	public boolean isDisplayed() {
		return driver.findElements(userAgreementPage).size() > 0;
	}
}