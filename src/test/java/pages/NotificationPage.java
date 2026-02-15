package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class NotificationPage {
	private AndroidDriver driver;
	private WebDriverWait wait;

	public NotificationPage(AndroidDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		System.out.println("NotificationPage initialized");
	}

	By notificationHeader = By.xpath("//android.widget.TextView[@text='Bildirimler']");
	
	public boolean isDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(notificationHeader)).isDisplayed();
	}
}
