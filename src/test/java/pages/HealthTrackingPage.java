package pages;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HealthTrackingPage extends BasePage {
    public HealthTrackingPage(AndroidDriver driver) {
        super(driver);
    }

    private By healthTrackingTitle= AppiumBy.accessibilityId("health_tracking_title");

    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(healthTrackingTitle)).isDisplayed();
    }

}