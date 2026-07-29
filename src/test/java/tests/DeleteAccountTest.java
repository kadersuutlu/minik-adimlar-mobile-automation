package tests;

import base.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Feature("Profil - Hesap Silme")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeleteAccountTest extends BaseTest {

    private static String disposableEmail;

    @BeforeAll
    public void setupAccountAndNavigateToProfileOnce() {
        resetApp();
        disposableEmail = flow.registerDisposableUserForDelete();
        pages.registerPage().dismissSuccessDialog();
        pages.registerPage().dismissAutofillSavePopupIfPresent();

        pages.addFirstBabyPage().fillBabyForm("delete", "17/02/2024", "girl");
        pages.addFirstBabyPage().clickSubmit();

        flow.passMainOnboarding();

        assertTrue(pages.homePage().isDisplayed(), "Kayıt sonrası ana sayfa yüklenemedi!");

        pages.homePage().clickProfileIcon();
        assertTrue(pages.profilePage().isDisplayed(), "Profil sayfasına yönlendirilemedi!");
    }

    @Test
    @Order(1)
    @DisplayName("Hesap silme iptal edilince profil sayfasında kalınmalı")
    @Severity(SeverityLevel.NORMAL)
    @Story("Hesap silme")
    public void shouldStayOnProfilePageWhenCancelDeleteAccount() {
        pages.profilePage().clickProfileDeleteAccountButton();
        assertTrue(pages.profilePage().isDeleteAccountConfirmDisplayed(), "Hesap silme onay pop-up'ı görüntülenemedi!");

        pages.profilePage().clickDeleteAccountCancelButton();

        assertTrue(pages.profilePage().isDisplayed(), "Hesap silme iptal edilmesine rağmen Profile sayfasından çıkıldı!");
    }

    @Test
    @Order(2)
    @DisplayName("Hesap silme onaylanınca onboarding sayfasına yönlendirilmeli")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Hesap silme")
    public void shouldNavigateToLoginPageWhenConfirmDeleteAccount() {
        pages.profilePage().clickProfileDeleteAccountButton();
        assertTrue(pages.profilePage().isDeleteAccountConfirmDisplayed(), "Hesap silme onay pop-up'ı görüntülenemedi!");

        pages.profilePage().clickDeleteAccountConfirmButton();
        pages.registerPage().dismissSuccessDialog();

        assertTrue(pages.secondPage().isLoginButtonDisplayed(), "Hesap silme sonrası onboarding sayfası açılmadı!");
    }
}