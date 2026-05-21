package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class MyBabyPage extends BasePage{
	
	public MyBabyPage(AndroidDriver driver) {
		super(driver);
		System.out.println("Bebeğim initialized");
	}

	By myBabyPageHeader = AppiumBy.accessibilityId("baby_main_title");	
	
	public boolean isDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(myBabyPageHeader)).isDisplayed();
	}
}