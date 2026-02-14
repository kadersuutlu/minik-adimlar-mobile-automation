package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class ContentsPage {

	private AndroidDriver driver;
	private WebDriverWait wait;

	public ContentsPage(AndroidDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		System.out.println("ContentsForBabyListPage initialized");
	}

	private By contentListTabForBaby = AppiumBy.accessibilityId("content_list_tab_for_baby");
	private By contentListTabForParent = AppiumBy.accessibilityId("content_list_tab_for_parent");

	private By contentTitles = AppiumBy
			.xpath("//android.view.ViewGroup[contains(@content-desc,'content_item_')]//android.widget.TextView[1]");

	public String getFirstContentTitle() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(contentTitles));
		return driver.findElements(contentTitles).get(0).getText();
	}

}