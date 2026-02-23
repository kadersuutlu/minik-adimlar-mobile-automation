package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class ReadingListPage extends BasePage{

	public ReadingListPage(AndroidDriver driver) {
		super(driver);
		System.out.println("ReadingListPage initialized");
	}
	
	By readingListHeader=AppiumBy.accessibilityId("reading_list_title");
	
	public boolean isDisplayed() {
		return driver.findElements(readingListHeader).size() > 0;
	}
	
	public boolean isContentPresent(String expectedTitle) {

	    List<WebElement> titles = driver.findElements(readingListHeader);

	    for (WebElement title : titles) {
	        if (title.getText().equals(expectedTitle)) {
	            return true;
	        }
	    }
	    return false;
	}
}