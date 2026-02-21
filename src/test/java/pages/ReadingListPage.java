package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class ReadingListPage {
	private AndroidDriver driver;
	private WebDriverWait wait;

	public ReadingListPage(AndroidDriver driver) {
		this.driver=driver;
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(15));
		System.out.println("ReadingListPage initialized");
	}
	
	By readingListHeader=AppiumBy.accessibilityId("reading_list_title");
	
	public boolean isDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(readingListHeader)).isDisplayed();
	}
}
