package pages;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SleepTrackingPage extends BasePage {
    public SleepTrackingPage(AndroidDriver driver) {
        super(driver);
    }

    private By sleepTrackingTitle= AppiumBy.accessibilityId("sleep_tracking_title");

    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(sleepTrackingTitle)).isDisplayed();
    }
}
