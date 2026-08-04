package pages;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NutritionTrackingPage extends BasePage {
    public NutritionTrackingPage(AndroidDriver driver) {
        super(driver);
    }

    private By nutritionTrackingTitle= AppiumBy.accessibilityId("nutrition_tracking_title");

    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(nutritionTrackingTitle)).isDisplayed();
    }
}