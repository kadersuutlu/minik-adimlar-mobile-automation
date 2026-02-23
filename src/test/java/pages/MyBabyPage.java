package pages;

import org.openqa.selenium.By;

import base.BasePage;
import io.appium.java_client.android.AndroidDriver;

public class MyBabyPage extends BasePage{
	
	public MyBabyPage(AndroidDriver driver) {
		super(driver);
		System.out.println("ContentsForBabyListPage initialized");
	}

	private By myBabyPageHeader = By.xpath("//*[contains(@text,'Merhaba')]");
	
	public boolean isDisplayed() {
		return driver.findElements(myBabyPageHeader).size() > 0;
	}
}