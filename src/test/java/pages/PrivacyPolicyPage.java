package pages;

import org.openqa.selenium.By;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class PrivacyPolicyPage extends BasePage {

	public PrivacyPolicyPage(AndroidDriver driver) {
		super(driver);
		System.out.println("Gizlilik Sözleşmesi initialized");
	}

	By privacyPolicyTitle = AppiumBy.accessibilityId("privacy_policy_title");

	public boolean isDisplayed() {
		return driver.findElements(privacyPolicyTitle).size() > 0;
	}
}