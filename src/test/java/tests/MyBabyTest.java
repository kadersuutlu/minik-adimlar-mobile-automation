package tests;

import base.BaseTest;
import data.TestData;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Feature("Bebeğim")
public class MyBabyTest extends BaseTest {

    @BeforeAll
    public void loginAndPassOnboardingOnce() {
        resetApp();
        flow.loginAndCleanStart(
                TestData.LOG_USER_WITH_BABY_EMAIL,
                TestData.LOG_USER_WITH_BABY_PASSWORD
        );

        assertTrue(pages.homePage().isDisplayed(), "Onboarding sonrası ana sayfa yüklenemedi!");
        pages.homePage().clickNavigationMyBaby();
        assertTrue(pages.myBabyPage().isDisplayed(), "Bebeğim sayfasına geri dönülemedi!");
    }

    @Test
    @DisplayName("Bebek kartına tıklanınca bebek detayı görüntülenmeli")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigasyon")
    public void babyCardShouldBeClickable() {
        pages.myBabyPage().clickBabyMainCard();

        assertTrue(pages.myBabyPage().isDisplayed(), "Bebek kartına tıklandıktan sonra bebeğim sayfası bozuldu");
    }

    @Test
    @DisplayName("Bebek ekle butonuna tıklanınca bebek ekle modalı görünmeli")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigasyon")
    public void addBabyModalShouldBeVisibleWhenClickAddBabyButton() {
        pages.myBabyPage().clickBabyMainAddBabyButton();

        assertTrue(pages.addBabyPage().isDisplayed(), "Add Baby modal did not appear");
    }

    @Test
    @DisplayName("Uyku takibi butonuna tıklanınca uyku takibi sayfası açılmalı")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigasyon")
    public void navigateToSleepTrackingPage() {
        pages.myBabyPage().clickBabyMainSleepTracking();

        assertTrue(pages.sleepTrackingPage().isDisplayed(), "my baby page did not redirect to sleep tracking page");
    }

    @Test
    @DisplayName("Beslenme takibi butonuna tıklanınca beslenme takibi sayfası açılmalı")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigasyon")
    public void navigateToNutritionTrackingPage() {
        pages.myBabyPage().clickBabyMainNutritionTracking();

        assertTrue(pages.nutritionTrackingPage().isDisplayed(), "my baby page did not redirect to nutrition tracking page");
    }

    @Test
    @DisplayName("Bez değişimi takibi butonuna tıklanınca bez değişimi takibi sayfası açılmalı")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigasyon")
    public void navigateToDiaperTrackingPage() {
        pages.myBabyPage().clickBabyMainDiaperTracking();

        assertTrue(pages.diaperTrackingPage().isDisplayed(), "my baby page did not redirect to diaper tracking page");
    }

    @Test
    @DisplayName("Büyüme takibi butonuna tıklanınca büyüme takibi sayfası açılmalı")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigasyon")
    public void navigateToGrowthTrackingPage() {
        pages.myBabyPage().clickBabyMainGrowthTracking();

        assertTrue(pages.growthTrackingPage().isDisplayed(), "my baby page did not redirect to growth tracking page");
    }

    @Test
    @DisplayName("Sağlık takibi butonuna tıklanınca sağlık takibi sayfası açılmalı")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigasyon")
    public void navigateToHealthTrackingPage() {
        pages.myBabyPage().clickBabyMainHealthTracking();

        assertTrue(pages.healthTrackingPage().isDisplayed(), "my baby page did not redirect to health tracking page");
    }

    @Test
    @DisplayName("İlkler takibi butonuna tıklanınca ilkler takibi sayfası açılmalı")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigasyon")
    public void navigateToFirstsTrackingPage() {
        pages.myBabyPage().clickBabyMainFirstsTracking();

        assertTrue(pages.firstTrackingPage().isDisplayed(), "my baby page did not redirect to firsts tracking page");
    }

    @AfterEach
    public void returnToMyBabyPage() {
        if (!pages.myBabyPage().isQuicklyDisplayed()) {
            driver.navigate().back();
        }
        assertTrue(pages.myBabyPage().isDisplayed(), "Test sonrası Bebeğim sayfasına dönülemedi!");
    }
}