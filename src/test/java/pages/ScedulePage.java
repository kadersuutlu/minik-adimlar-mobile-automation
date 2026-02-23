package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;
import io.appium.java_client.android.AndroidDriver;

public class ScedulePage extends BasePage{
	
	public ScedulePage(AndroidDriver driver) {
		super(driver);
		System.out.println("NotificationPage initialized");
	}

	By scedulePageHeader = By.xpath("//android.widget.TextView[@text='Takvim']");
	
	public boolean isDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(scedulePageHeader)).isDisplayed();
	}
}