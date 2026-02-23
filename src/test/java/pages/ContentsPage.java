package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class ContentsPage extends BasePage{

	public ContentsPage(AndroidDriver driver) {
		super(driver);
		System.out.println("ContentsForBabyListPage initialized");
	}

	By contentsHeader = AppiumBy.accessibilityId("content_list_title");

	private By contentListTabForBaby = AppiumBy.accessibilityId("content_list_tab_for_baby");
	private By contentListTabForParent = AppiumBy.accessibilityId("content_list_tab_for_parent");

	private By contentListReadingListIcon = AppiumBy.accessibilityId("content_list_reading_list_icon");
	private By contetListNotificationIcon = AppiumBy.accessibilityId("content_list_notification_icon");

	private By contentListSearchInput = AppiumBy.accessibilityId("content_list_search_input");
	private By contentListAddReadingListIcon = AppiumBy.accessibilityId("content_item_reading_list_icon_0");

	private By contentTitles = AppiumBy
			.xpath("//android.view.ViewGroup[contains(@content-desc,'content_item_')]//android.widget.TextView[1]");

	public boolean isDisplayed() {
		return driver.findElements(contentsHeader).size() > 0;
	}

	public void waitForContentsPage() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(contentsHeader));
	}

	public String getFirstContentTitle() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(contentTitles));
		return driver.findElements(contentTitles).get(0).getText();
	}

	public void clickBabyContentsTab() {
		wait.until(ExpectedConditions.elementToBeClickable(contentListTabForBaby)).click();
	}

	public void clickParentContentsTab() {
		wait.until(ExpectedConditions.elementToBeClickable(contentListTabForParent)).click();
	}

	public void clickContentListReadingListIcon() {
		wait.until(ExpectedConditions.elementToBeClickable(contentListReadingListIcon)).click();
	}

	public void clickContentListNotificationIcon() {
		wait.until(ExpectedConditions.elementToBeClickable(contetListNotificationIcon)).click();
	}

	public void clickContentListAddReadingListIcon() {
		wait.until(ExpectedConditions.elementToBeClickable(contentListAddReadingListIcon)).click();
	}

	public void enterSearchInput(String keyword) {
		driver.findElement(contentListSearchInput).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(contentListSearchInput)).sendKeys(keyword);
	}
}