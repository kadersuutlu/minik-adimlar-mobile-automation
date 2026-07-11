package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class HomePage extends BasePage {

	public HomePage(AndroidDriver driver) {
		super(driver);
		System.out.println("Anasayfa initialized");
	}

	// Ana sayfanın yüklendiğini doğrulamak için en güvenilir element
	private final By homeHeader = AppiumBy.accessibilityId("home_baby_feeling_card");

	/**
	 * Profil ikonu için XPath kullanımı (Eğer accessibilityId eklendiyse onunla
	 * değiştirilmesi önerilir)
	 */
	private final By profileIcon = By.xpath("//com.horcrux.svg.RectView[2]");

	private final By homeBabyFeelingCard = AppiumBy.accessibilityId("Bebeğiniz Bugün Nasıl?");
	private final By homeNotificationIcon = AppiumBy.accessibilityId("home_notification_icon");
	private final By homeBabyContentSeeAllText = AppiumBy.accessibilityId("home_baby_content_see_all_text");
	private final By homeParentContentSeeAllText = AppiumBy.accessibilityId("home_parent_content_see_all_text");
	private final By homeBabyCardAddIcon = AppiumBy.accessibilityId("home_baby_card_add_icon");

	// Alt Menü Navigasyon
	private final By navigationContents = AppiumBy.accessibilityId("İçerikler");
	private final By navigationHome = AppiumBy.accessibilityId("Ana Sayfa");
	private final By navigationMyBaby = AppiumBy.accessibilityId("Bebeğim");
	private final By navigationSchedule = AppiumBy.accessibilityId("Takvim");

	// Onboarding Butonları
	private final By onboardingNextButton = AppiumBy.accessibilityId("onboarding_next_button");
	private final By onboardingDoneButton = AppiumBy.accessibilityId("onboarding_done_button");

	// Sayfanın yüklendiğini kontrol eder.
	public boolean isDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(homeHeader)).isDisplayed();
	}

	public void waitForHomePage() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(homeHeader));
	}

	/**
	 * Onboarding durumunu kontrol eder. Döngü içinde hızlı çalışması için
	 * findElements kullanılmıştır.
	 */
    public int getOnboardingStatus() {
        if (!driver.findElements(onboardingNextButton).isEmpty()) {
            return 1;
        } else if (!driver.findElements(onboardingDoneButton).isEmpty()) {
            return 2;
        }
        return 0;
    }

    public void clickOnboardingNext() {
        wait.until(ExpectedConditions.elementToBeClickable(onboardingNextButton)).click();
        // tıklama sonrası ekranın güncellenmesini bekle
        wait.until(driver ->
                driver.findElements(onboardingNextButton).isEmpty()
                        || driver.findElements(onboardingDoneButton).size() > 0
                        || true // aşağıdaki nottaki gibi düzelt
        );
    }

    public void clickOnboardingDone() {
        wait.until(ExpectedConditions.elementToBeClickable(onboardingDoneButton)).click();
    }

	// --- Tıklama Aksiyonları ---

	public void clickProfileIcon() {
		wait.until(ExpectedConditions.elementToBeClickable(profileIcon)).click();
	}

	public void clickHomeBabyFeelingCard() {
		wait.until(ExpectedConditions.elementToBeClickable(homeBabyFeelingCard)).click();
	}

	public void clickHomeNotificationIcon() {
		wait.until(ExpectedConditions.elementToBeClickable(homeNotificationIcon)).click();
	}

	public void clickHomeBabyContentSeeAllText() {
		wait.until(ExpectedConditions.elementToBeClickable(homeBabyContentSeeAllText)).click();
	}

	public void clickHomeParentContentSeeAllText() {
		wait.until(ExpectedConditions.elementToBeClickable(homeParentContentSeeAllText)).click();
	}

	public void clickHomeBabyCardAddIcon() {
		wait.until(ExpectedConditions.elementToBeClickable(homeBabyCardAddIcon)).click();
	}

	public void clickNavigationContents() {
		wait.until(ExpectedConditions.elementToBeClickable(navigationContents)).click();
	}

	public void clickNavigationHome() {
		wait.until(ExpectedConditions.elementToBeClickable(navigationHome)).click();
	}

	public void clickNavigationMyBaby() {
		wait.until(ExpectedConditions.elementToBeClickable(navigationMyBaby)).click();
	}

	public void clickNavigationSchedule() {
		wait.until(ExpectedConditions.elementToBeClickable(navigationSchedule)).click();
	}
}