package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyBabyPage extends BasePage{
	
	public MyBabyPage(AndroidDriver driver) {
		super(driver);
		System.out.println("Bebeğim initialized");
	}

	By myBabyPageHeader = AppiumBy.accessibilityId("baby_main_title");

    private By babyMainCard=AppiumBy.accessibilityId("baby_main_baby_card_0");
    private By babyMainAddBabyButton=AppiumBy.accessibilityId("baby_main_add_baby_button");
    private By babyMainSleepTracking=AppiumBy.accessibilityId("baby_main_sleep_tracking_button");
    private By babyMainNutritionTracking=AppiumBy.accessibilityId("baby_main_nutrition_tracking_button");
    private By babyMainDiaperTracking=AppiumBy.accessibilityId("baby_main_diaper_tracking_button");
    private By babyMainGrowthTracking=AppiumBy.accessibilityId("baby_main_growth_tracking_button");
    private By babyMainHealthTracking=AppiumBy.accessibilityId("baby_main_health_tracking_button");
    private By babyMainFirstsTracking=AppiumBy.accessibilityId("baby_main_firsts_tracking_button");
	
	public boolean isDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(myBabyPageHeader)).isDisplayed();
	}

    @Step("Bebek kartına tıklandı")
    public void clickBabyMainCard() {
        click(babyMainCard);
    }

    @Step("Bebek ekle butonuna tıklandı")
    public void clickBabyMainAddBabyButton() {
        click(babyMainAddBabyButton);
    }

    @Step("Uyku takibi butonuna tıklandı")
    public void clickBabyMainSleepTracking() {
        click(babyMainSleepTracking);
    }

    @Step("Beslenme takibi butonuna tıklandı")
    public void clickBabyMainNutritionTracking() {
        click(babyMainNutritionTracking);
    }

    @Step("Bez değişimi takibi butonuna tıklandı")
    public void clickBabyMainDiaperTracking() {
        click(babyMainDiaperTracking);
    }

    @Step("Büyüme takibi butonuna tıklandı")
    public void clickBabyMainGrowthTracking() {
        click(babyMainGrowthTracking);
    }

    @Step("Sağlık takibi butonuna tıklandı")
    public void clickBabyMainHealthTracking() {
        click(babyMainHealthTracking);
    }

    @Step("İlkler takibi butonuna tıklandı")
    public void clickBabyMainFirstsTracking() {
        click(babyMainFirstsTracking);
    }

    @Step("Bebeğim sayfasında olup olmadığı hızlıca kontrol ediliyor")
    public boolean isQuicklyDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            return shortWait.until(ExpectedConditions.visibilityOfElementLocated(myBabyPageHeader)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}