package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class HomePage extends BasePage {

	public HomePage(AndroidDriver driver) {
		super(driver);
		System.out.println("Home initialized");
	}

	By homeHeader = AppiumBy.accessibilityId("home_baby_feeling_card");
	
	private By homeBabyFeelingCard=AppiumBy.accessibilityId("Bebeğiniz Bugün Nasıl?");
	
	private By homeNotificationId=AppiumBy.accessibilityId("home_notification_icon");
	
	private By homeBabyContentSeeAllText=AppiumBy.accessibilityId("home_baby_content_see_all_text");
	
	private By homeParentContentSeeAllText=AppiumBy.accessibilityId("home_parent_content_see_all_text");
	
	private By homeBabyCardAddIcon=AppiumBy.accessibilityId("home_baby_card_add_icon");
	
	private By navigationContents = AppiumBy.accessibilityId("İçerikler");
	private By navigationHome = AppiumBy.accessibilityId("Ana Sayfa");
	private By navigationMyBaby = AppiumBy.accessibilityId("Bebeğim");
	private By navigationScedule = AppiumBy.accessibilityId("Takvim");
	
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
	
	public void clickNavigationContents() {
		wait.until(ExpectedConditions.elementToBeClickable(navigationContents)).click();
	}
	
	public void clickNavigationHome() {
		wait.until(ExpectedConditions.elementToBeClickable(navigationHome)).click();
	}
	
	public void clickNavigationMyBaby() {
		wait.until(ExpectedConditions.elementToBeClickable(navigationMyBaby)).click();
	}
	
	public void clickNavigationScedule() {
		wait.until(ExpectedConditions.elementToBeClickable(navigationScedule)).click();
	}
}