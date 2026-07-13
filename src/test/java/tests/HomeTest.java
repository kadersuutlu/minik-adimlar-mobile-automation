package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import api.ContentApi;
import base.AppFlowManager;
import base.BaseTest;
import data.TestData;

@Feature("Ana Sayfa")
public class HomeTest extends BaseTest {

    @BeforeAll
    public void loginAndPassOnboardingOnce() {
        resetApp();
        resetApp();
        flow.loginAndCleanStart(
                TestData.LOG_USER_WITH_BABY_EMAIL,
                TestData.LOG_USER_WITH_BABY_PASSWORD
        );

        assertTrue(pages.homePage().isDisplayed(), "Onboarding sonrası ana sayfa yüklenemedi!");
    }

    @BeforeEach
    public void backToHome() {
        pages.homePage().clickNavigationHome();
        assertTrue(pages.homePage().isDisplayed(), "Ana sayfaya geri dönülemedi!");
    }

    @Test
    @Disabled("UI ile API'den gelen içerik sıralaması eşleşmiyor, backend ile netleştirilecek")
    @DisplayName("Bebek içeriği tümünü gör, API ile eşleşmeli")
    @Severity(SeverityLevel.NORMAL)
    @Story("İçerik doğrulama")
    public void babyContentShouldMatchApi() {
        pages.homePage().clickHomeBabyContentSeeAllText();

        String uiTitle = pages.contentsPage().getFirstContentTitle();

        String apiTitle = ContentApi.getFirstContentTitleByAudience(
                "BABY",
                TestData.LOG_USER_WITH_BABY_EMAIL,
                TestData.LOG_USER_WITH_BABY_PASSWORD
        );

        assertEquals(uiTitle, apiTitle);
    }

    @Test
    @Disabled("UI ile API'den gelen içerik sıralaması eşleşmiyor, backend ile netleştirilecek")
    @DisplayName("Ebeveyn içeriği tümünü gör, API ile eşleşmeli")
    @Severity(SeverityLevel.NORMAL)
    @Story("İçerik doğrulama")
    public void parentContentsShouldMatchApi() {
        pages.homePage().clickHomeParentContentSeeAllText();

        String uiTitle = pages.contentsPage().getFirstContentTitle();

        String apiTitle = ContentApi.getFirstContentTitleByAudience(
                "PARENT",
                TestData.LOG_USER_WITH_BABY_EMAIL,
                TestData.LOG_USER_WITH_BABY_PASSWORD
        );

        assertEquals(uiTitle, apiTitle);
    }

    @Test
    @DisplayName("Bebeğiniz bugün nasıl kartına tıklanınca bebeğim sayfası açılmalı")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigasyon")
    public void myBabyPageOpenWhenClickHowIsYourBabyTodayButton() {
        pages.homePage().clickHomeBabyFeelingCard();

        assertTrue(pages.myBabyPage().isDisplayed(), "home page did not redirect to my baby page");
    }

    @Test
    @DisplayName("Bildirim ikonuna tıklanınca bildirimler sayfası açılmalı")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigasyon")
    public void notificationPageOpenWhenClickNotificationButton() {
        pages.homePage().clickHomeNotificationIcon();

        assertTrue(pages.notificationPage().isDisplayed(), "home page did not redirect to notification page");
    }

    @Test
    @DisplayName("Bebek ekle ikonuna tıklanınca bebek ekle modalı görünmeli")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigasyon")
    public void addBabyModalShouldBeVisibleWhenClickAddBabyButton() {
        pages.homePage().clickHomeBabyCardAddIcon();

        assertTrue(pages.addBabyPage().isDisplayed(), "Add Baby modal did not appear");
    }

    @Test
    @DisplayName("Bebeğim navigasyonuna tıklanınca bebeğim sayfası açılmalı")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigasyon")
    public void navigateToMyBabiesPage() {
        pages.homePage().clickNavigationMyBaby();

        assertTrue(pages.myBabyPage().isDisplayed(), "home page did not redirect to my baby page");
    }

    @Test
    @DisplayName("İçerikler navigasyonuna tıklanınca içerikler sayfası açılmalı")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigasyon")
    public void navigateToContentsPage() {
        pages.homePage().clickNavigationContents();

        assertTrue(pages.contentsPage().isDisplayed(), "home page did not redirect to contents page");
    }

    @Test
    @DisplayName("Takvim navigasyonuna tıklanınca takvim sayfası açılmalı")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigasyon")
    public void navigateToScedulePage() {
        pages.homePage().clickNavigationSchedule();

        assertTrue(pages.scedulePage().isDisplayed(), "home page did not redirect to scedule page");
    }
}