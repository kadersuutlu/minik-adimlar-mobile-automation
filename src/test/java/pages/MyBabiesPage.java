package pages;

import org.openqa.selenium.By;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class MyBabiesPage extends BasePage {

	public MyBabiesPage(AndroidDriver driver) {
		super(driver);
		System.out.println("MyBabiesPage initialized");
	}

	By myBabiesTitle = AppiumBy.accessibilityId("personal_info_title");

	public boolean isDisplayed() {
		return driver.findElements(myBabiesTitle).size() > 0;
	}
}