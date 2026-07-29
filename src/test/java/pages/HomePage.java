package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

	public HomePage(AndroidDriver driver) {
		super(driver);
		System.out.println("Anasayfa initialized");
	}

    private final WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(30));

    private final By homeHeader = AppiumBy.accessibilityId("home_baby_feeling_card");

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
    private final By onboardingNextButton = By.xpath("//*[starts-with(@content-desc,'onboarding_next_button')]");
    //onboarding_next_button_1, onboarding_next_button_2,onboarding_next_button_3, onboarding_next_button_4, onboarding_next_button_5, onboarding_next_button_6, onboarding_next_button_7
    private final By onboardingDoneButton = AppiumBy.accessibilityId("onboarding_done_button");

    @Step("Ana sayfanın görüntülendiği doğrulanıyor")
    public boolean isDisplayed() {
        int attempts = 0;
        while (attempts < 3) {
            try {
                return longWait.until(ExpectedConditions.visibilityOfElementLocated(homeHeader)).isDisplayed();
            } catch (StaleElementReferenceException e) {
                attempts++;
            } catch (TimeoutException e) {
                System.out.println("HOME BULUNAMADI, PAGE SOURCE:");
                System.out.println(driver.getPageSource());
                throw e;
            }
        }
        throw new StaleElementReferenceException("Ana sayfa elementi 3 denemede stabilize olmadı: " + homeHeader);
    }

    @Step("Ana sayfanın yüklenmesi bekleniyor")
    public void waitForHomePage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(homeHeader));
    }

    @Step("Onboarding durumu kontrol ediliyor")
    public int getOnboardingStatus() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(4));
            shortWait.until(d ->
                    !d.findElements(onboardingNextButton).isEmpty()
                            || !d.findElements(onboardingDoneButton).isEmpty()
            );
        } catch (TimeoutException e) {
            // 4 saniyede onboarding hiç gelmediyse, gerçekten yok demektir
        }

        if (!driver.findElements(onboardingNextButton).isEmpty()) {
            return 1;
        } else if (!driver.findElements(onboardingDoneButton).isEmpty()) {
            return 2;
        }
        return 0;
    }

    @Step("Onboarding sonraki butonuna tıklandı")
    public void clickOnboardingNext() {
        click(onboardingNextButton);
    }

    @Step("Onboarding tamamla butonuna tıklandı")
    public void clickOnboardingDone() {
        click(onboardingDoneButton);
    }

    @Step("Profil ikonuna tıklandı")
    public void clickProfileIcon() {
        click(profileIcon);
    }

    @Step("Bebeğiniz bugün nasıl kartına tıklandı")
    public void clickHomeBabyFeelingCard() {
        click(homeBabyFeelingCard);
    }

    @Step("Bildirim ikonuna tıklandı")
    public void clickHomeNotificationIcon() {
        click(homeNotificationIcon);
    }

    @Step("Bebek içeriği tümünü gör metnine tıklandı")
    public void clickHomeBabyContentSeeAllText() {
        click(homeBabyContentSeeAllText);
    }

    @Step("Ebeveyn içeriği tümünü gör metnine tıklandı")
    public void clickHomeParentContentSeeAllText() {
        click(homeParentContentSeeAllText);
    }

    @Step("Bebek ekle ikonuna tıklandı")
    public void clickHomeBabyCardAddIcon() {
        click(homeBabyCardAddIcon);
    }

    @Step("İçerikler navigasyonuna tıklandı")
    public void clickNavigationContents() {
        click(navigationContents);
    }

    @Step("Ana sayfa navigasyonuna tıklandı")
    public void clickNavigationHome() {
        click(navigationHome);
    }

    @Step("Bebeğim navigasyonuna tıklandı")
    public void clickNavigationMyBaby() {
        click(navigationMyBaby);
    }

    @Step("Takvim navigasyonuna tıklandı")
    public void clickNavigationSchedule() {
        click(navigationSchedule);
    }
}