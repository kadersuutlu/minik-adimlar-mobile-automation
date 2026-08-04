package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import data.TestData;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import base.BaseTest;

@Feature("Kişisel Bilgilerim")
public class PersonalInfoTest extends BaseTest {

    private String dynamicEmail;
    private String dynamicPhone;
    private String dynamicName;
    private String dynamicPassword;

    @BeforeEach
    public void setupPage() {
        resetApp();

        flow.loginAndCleanStart(TestData.PERSONAL_INFO_TEST_EMAIL, TestData.PERSONAL_INFO_TEST_PASSWORD);

        assertTrue(pages.homePage().isDisplayed(), "Onboarding sonrası ana sayfa yüklenemedi!");
        pages.homePage().clickProfileIcon();
        pages.profilePage().clickProfilePersonalInfoButton();

        dynamicEmail = TestData.generateEmail();
        dynamicPhone = TestData.generatePhoneNumber();
        dynamicName = TestData.generateName();
        dynamicPassword = TestData.generatePassword();
    }

    @Test
    @DisplayName("Kişisel bilgiler sayfası görüntülenmeli")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Kişisel bilgiler ekranı görünürlüğü")
    public void personalInfoScreenShouldBeDisplayed() {
        assertTrue(pages.personalInfoPage().isDisplayed(), "Kişisel Bilgiler sayfası görüntülenemedi!");
    }

    @Test
    @DisplayName("Bilgiler güncellenince e-posta değişikliği onayı ile onboarding sayfasına yönlendirilmeli")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Bilgi güncelleme")
    public void shouldUpdatePersonalInfoSuccessfully() {
        pages.personalInfoPage().enterName(dynamicName);
        pages.personalInfoPage().enterEmail(dynamicEmail);
        pages.personalInfoPage().enterPhone(dynamicPhone);
        driver.hideKeyboard();
        pages.personalInfoPage().clickSave();

        assertTrue(pages.secondPage().isLoginButtonDisplayed(), "E-posta değişikliği sonrası onboarding sayfasına yönlendirilmedi!");

        TestData.PERSONAL_INFO_TEST_EMAIL = dynamicEmail;
    }

    @Test
    @DisplayName("E-posta değişikliği iptal edilince profil sayfasında kalınmalı")
    @Severity(SeverityLevel.NORMAL)
    @Story("Bilgi güncelleme")
    public void shouldStayOnProfilePageWhenCancelEmailChange() {
        pages.personalInfoPage().enterEmail(dynamicEmail);
        driver.hideKeyboard();

        pages.personalInfoPage().clickCancel();

        assertTrue(pages.profilePage().isDisplayed(), "İptal sonrası Profil sayfasından çıkıldı!");
    }

    @Test
    @DisplayName("Geçersiz e-posta formatında hata mesajı görüntülenmeli")
    @Severity(SeverityLevel.NORMAL)
    @Story("Doğrulama")
    public void shouldShowErrorForInvalidEmailFormat() {
        pages.personalInfoPage().enterEmail(TestData.REG_INVALID_EMAIL);

        String errorText = pages.personalInfoPage().getEmailErrorText();

        assertTrue(errorText.contains("Geçerli"), "Geçersiz e-posta hata mesajı görüntülenmeli!");
    }

    @Test
    @DisplayName("Vazgeç butonuna basılınca değişiklikler kaydedilmeden profil sayfasına dönülmeli")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigasyon")
    public void shouldDiscardChangesWhenCancelClicked() {
        pages.personalInfoPage().enterName(dynamicName);
        pages.personalInfoPage().clickCancel();

        assertTrue(pages.profilePage().isDisplayed(), "Vazgeç sonrası Profil sayfasına dönülmedi!");
    }
}