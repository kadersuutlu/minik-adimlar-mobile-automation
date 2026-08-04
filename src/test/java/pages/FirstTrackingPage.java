package pages;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FirstTrackingPage extends BasePage {
    public FirstTrackingPage(AndroidDriver driver) {
        super(driver);
    }

    private By firstTrackingTitle= AppiumBy.accessibilityId("firsts_main_title");

    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstTrackingTitle)).isDisplayed();
    }
}