package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;
import io.appium.java_client.android.AndroidDriver;

public class NotificationPage extends BasePage{

	public NotificationPage(AndroidDriver driver) {
		super(driver);
		System.out.println("Bildirimler initialized");
	}

	By notificationHeader = By.xpath("//android.widget.TextView[@text='Bildirimler']");
	
	public boolean isDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(notificationHeader)).isDisplayed();
	}
}