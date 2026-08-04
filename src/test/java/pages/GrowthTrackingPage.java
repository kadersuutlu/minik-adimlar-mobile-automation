package pages;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GrowthTrackingPage extends BasePage {
    public GrowthTrackingPage(AndroidDriver driver) {
        super(driver);
    }

    private By growthTrackingTitle= AppiumBy.accessibilityId("growth_tracking_title");

    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(growthTrackingTitle)).isDisplayed();
    }
}