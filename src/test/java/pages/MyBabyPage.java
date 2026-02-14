package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class MyBabyPage {

	private AndroidDriver driver;
	private WebDriverWait wait;

	public MyBabyPage(AndroidDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		System.out.println("ContentsForBabyListPage initialized");
	}

	private By myBabyPageHeader = By.xpath("//*[contains(@text,'Merhaba')]");
	
	public boolean isDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(myBabyPageHeader)).isDisplayed();
	}
}