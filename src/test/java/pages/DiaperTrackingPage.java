package pages;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DiaperTrackingPage extends BasePage {
    public DiaperTrackingPage(AndroidDriver driver) {
        super(driver);
    }

    private By diaperTrackingTitle= AppiumBy.accessibilityId("diaper_tracking_title");

    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(diaperTrackingTitle)).isDisplayed();
    }
}