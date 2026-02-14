package pages;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.AppFlowManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class HomePage {
	private AndroidDriver driver;
	private WebDriverWait wait;

	public HomePage(AndroidDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		System.out.println("Home initialized");
	}

	By homeHeader = AppiumBy.accessibilityId("home_baby_feeling_card");
	
	private By homeBabyFeelingCard=AppiumBy.accessibilityId("Bebeğiniz Bugün Nasıl?");
	
	private By homeNotificationId=AppiumBy.accessibilityId("home_notification_icon");
	
	private By homeBabyContentSeeAllText=AppiumBy.accessibilityId("home_baby_content_see_all_text");
	
	private By homeParentContentSeeAllText=AppiumBy.accessibilityId("home_parent_content_see_all_text");
	
	private By homeBabyCardAddIcon=AppiumBy.accessibilityId("home_baby_card_add_icon");
	
	public boolean isDisplayed() {
		return driver.findElements(homeHeader).size() > 0;
	}
	
	public void waitForHomePage() {
	    wait.until(ExpectedConditions.visibilityOfElementLocated(homeHeader));
	}
	
	public void clickHomeBabyFeelingCard() {
		wait.until(ExpectedConditions.elementToBeClickable(homeBabyFeelingCard)).click();
	}
	
	public void clickHomeNotificationId() {
		wait.until(ExpectedConditions.elementToBeClickable(homeNotificationId)).click();
	}
	
	public void clickHomeBabyContentSeeAllText() {
		wait.until(ExpectedConditions.elementToBeClickable(homeBabyContentSeeAllText)).click();
	}
	
	public void clickHomeParentContentSeeAllText() {
		wait.until(ExpectedConditions.elementToBeClickable(homeParentContentSeeAllText)).click();
	}
	
	public void clickHomeBabyCardAddIcon() {
		wait.until(ExpectedConditions.elementToBeClickable(homeBabyCardAddIcon)).click();
	}
}