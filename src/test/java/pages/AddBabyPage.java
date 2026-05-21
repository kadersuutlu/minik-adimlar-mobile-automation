package pages;

import org.openqa.selenium.By;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AddBabyPage extends BasePage{

	public AddBabyPage(AndroidDriver driver) {
		super(driver);
		System.out.println("Bebek Ekle initialized");
	}

	By babyModalTitle = AppiumBy.accessibilityId("baby_modal_title");
	
	public boolean isDisplayed() {
		return driver.findElements(babyModalTitle).size() > 0;
	}
}