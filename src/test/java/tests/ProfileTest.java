package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import data.TestData;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import base.AppFlowManager;
import base.BaseTest;

@Feature("Profil")
public class ProfileTest extends BaseTest {

    @BeforeEach
    public void setupPage() {
        resetApp();
        flow.loginAndCleanStart(TestData.LOG_USER_WITH_BABY_EMAIL, TestData.LOG_USER_WITH_BABY_PASSWORD);
        assertTrue(pages.homePage().isDisplayed(), "Onboarding sonrası ana sayfa yüklenemedi!");
        pages.homePage().clickProfileIcon();
    }

    @Test
    @DisplayName("Profil sayfası görüntülenmeli")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Profil ekranı görünürlüğü")
    public void profileScreenShouldBeDisplayed() {
        assertTrue(pages.profilePage().isDisplayed(), "Profil sayfası görüntülenmeli");
    }

    @Test
    @DisplayName("Profil ekranındaki tüm butonlar görünür olmalı")
    @Severity(SeverityLevel.NORMAL)
    @Story("Profil ekranı görünürlüğü")
    public void allProfileButtonsShouldBeVisible() {
        assertTrue(pages.profilePage().areAllButtonsVisible(), "Profil ekranındaki tüm butonlar görünür olmalı");
    }

    @Test
    @DisplayName("Kişisel bilgilerim sayfasına gidilmeli")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigasyon")
    public void shouldNavigateToPersonalInfoPage() {
        pages.profilePage().clickProfilePersonalInfoButton();
        assertTrue(pages.personalInfoPage().isDisplayed(), "Kişisel bilgilerim sayfası görüntülenmeli");
    }

    @Test
    @DisplayName("Bebeklerim sayfasına gidilmeli")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigasyon")
    public void shouldNavigateToMyBabiesPage() {
        pages.profilePage().clickProfileMyBabiesButton();
        assertTrue(pages.myBabiesPage().isDisplayed(), "Bebeklerim sayfası görüntülenmeli");
    }

    @Test
    @DisplayName("Okuma listesi sayfasına gidilmeli")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigasyon")
    public void shouldNavigateToReadingListPage() {
        pages.profilePage().clickProfileReadingListButton();
        assertTrue(pages.readingListPage().isDisplayed(), "Okuma listesi sayfası görüntülenmeli");
    }

    @Test
    @DisplayName("Şifre değiştir sayfasına gidilmeli")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigasyon")
    public void shouldNavigateToChangePasswordPage() {
        pages.profilePage().clickProfileChangePasswordButton();
        assertTrue(pages.changePasswordPage().isDisplayed(), "Şifre değiştir sayfası görüntülenmeli");
    }

    @Test
    @DisplayName("Çıkış onaylanınca login sayfasına yönlendirilmeli")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Çıkış yapma")
    public void shouldNavigateToLoginPageWhenConfirmLogout() {
        pages.profilePage().clickProfileLogOutButton();

        assertTrue(pages.profilePage().isLogOutConfirmDisplayed(), "Logout onay pop-up'ı görüntülenemedi!");

        pages.profilePage().clickLogOutConfirmLogOutButton();

        assertTrue(pages.secondPage().isLoginButtonDisplayed(), "Logout sonrası onboarding sayfası açılmadı!");
    }

    @Test
    @DisplayName("Çıkış iptal edilince profil sayfasında kalınmalı")
    @Severity(SeverityLevel.NORMAL)
    @Story("Çıkış yapma")
    public void shouldStayOnProfilePageWhenCancelLogout() {
        pages.profilePage().clickProfileLogOutButton();
        assertTrue(pages.profilePage().isLogOutConfirmDisplayed(), "Logout onay pop-up'ı görüntülenemedi!");

        pages.profilePage().clickLogOutConfirmCancelButton();

        assertTrue(pages.profilePage().isDisplayed(), "Logout iptal edilmesine rağmen Profile sayfasından çıkıldı!");
    }
}