package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import base.BaseTest;
import data.TestData;
import utils.AccountCleanupUtil;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Feature("Kayıt Ol")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegisterTest extends BaseTest {

    private String registeredEmail;
    private String registeredPhoneNumber;

    @BeforeEach
    public void setupPage() {
        resetApp();

        driver.activateApp(APP_PACKAGE);
        flow.goToRegister();
    }

    @Test
    @DisplayName("Geçersiz email girilince hata mesajı gösterilmeli")
    @Description("Email formatı hatalı girildiğinde validation mesajı görünmeli")
    @Severity(SeverityLevel.NORMAL)
    @Story("Email validasyonu")
    public void registerWithInvalidEmailShowsError() {
        pages.registerPage().enterName(TestData.REG_VALID_NAME);
        driver.hideKeyboard();
        pages.registerPage().enterEmail(TestData.REG_INVALID_EMAIL);
        driver.hideKeyboard();

        assertTrue(pages.registerPage().getEmailErrorText().contains("geçerli"),
                "Geçersiz email hata mesajı görüntülenmeli");
    }

    @Test
    @DisplayName("Boş şifre girilince hata mesajı gösterilmeli")
    @Severity(SeverityLevel.NORMAL)
    @Story("Şifre validasyonu")
    public void registerWithEmptyPasswordShowsError() {
        pages.registerPage().enterPassword(TestData.REG_EMPTY_PASSWORD);
        pages.registerPage().enterName(TestData.REG_VALID_NAME);
        driver.hideKeyboard();

        assertTrue(pages.registerPage().getPasswordEmptyErrorText().contains("zorunlu"),
                "Şifre zorunlu hata mesajı görüntülenmeli");
    }

    @Test
    @DisplayName("Kısa şifre girilince minimum uzunluk hatası gösterilmeli")
    @Severity(SeverityLevel.NORMAL)
    @Story("Şifre validasyonu")
    public void registerWithShortPasswordShowsMinLengthError() {
        pages.registerPage().enterName(TestData.REG_VALID_NAME);
        driver.hideKeyboard();
        pages.registerPage().enterEmail(TestData.generateEmail());
        driver.hideKeyboard();
        pages.registerPage().enterPhone(TestData.REG_VALID_PHONE);
        driver.hideKeyboard();
        pages.registerPage().enterPassword(TestData.REG_SHORT_PASSWORD);
        driver.hideKeyboard();

        assertTrue(pages.registerPage().getPasswordMinLengthErrorText().contains("en az"),
                "Minimum uzunluk hata mesajı görüntülenmeli");
    }

    @Test
    @DisplayName("Büyük harf içermeyen şifre girilince hata mesajı gösterilmeli")
    @Severity(SeverityLevel.NORMAL)
    @Story("Şifre validasyonu")
    public void registerWithPasswordWithoutUppercaseShowsError() {
        pages.registerPage().enterName(TestData.REG_VALID_NAME);
        driver.hideKeyboard();
        pages.registerPage().enterEmail(TestData.generateEmail());
        driver.hideKeyboard();
        pages.registerPage().enterPhone(TestData.REG_VALID_PHONE);
        driver.hideKeyboard();
        pages.registerPage().enterPassword(TestData.REG_NO_UPPERCASE_PASSWORD);
        driver.hideKeyboard();

        assertTrue(pages.registerPage().getPasswordUppercaseErrorText().contains("büyük harf"),
                "Büyük harf hata mesajı görüntülenmeli");
    }

    @Test
    @DisplayName("Küçük harf içermeyen şifre girilince hata mesajı gösterilmeli")
    @Severity(SeverityLevel.NORMAL)
    @Story("Şifre validasyonu")
    public void registerWithPasswordWithoutLowercaseShowsError() {
        pages.registerPage().enterName(TestData.REG_VALID_NAME);
        driver.hideKeyboard();
        pages.registerPage().enterEmail(TestData.generateEmail());
        driver.hideKeyboard();
        pages.registerPage().enterPhone(TestData.REG_VALID_PHONE);
        driver.hideKeyboard();
        pages.registerPage().enterPassword(TestData.REG_NO_LOWERCASE_PASSWORD);
        driver.hideKeyboard();

        assertTrue(pages.registerPage().getPasswordLowercaseErrorText().contains("küçük harf"),
                "Küçük harf hata mesajı görüntülenmeli");
    }

    @Test
    @DisplayName("Özel karakter içermeyen şifre girilince hata mesajı gösterilmeli")
    @Severity(SeverityLevel.NORMAL)
    @Story("Şifre validasyonu")
    public void registerWithPasswordWithoutSpecialCharShowsError() {
        pages.registerPage().enterName(TestData.REG_VALID_NAME);
        driver.hideKeyboard();
        pages.registerPage().enterEmail(TestData.generateEmail());
        driver.hideKeyboard();
        pages.registerPage().enterPhone(TestData.REG_VALID_PHONE);
        driver.hideKeyboard();
        pages.registerPage().enterPassword(TestData.REG_NO_SPECIAL_CHAR_PASSWORD);
        driver.hideKeyboard();

        assertTrue(pages.registerPage().getPasswordSpecialCharErrorText().contains("özel karakter"),
                "Özel karakter hata mesajı görüntülenmeli");
    }

    @Test
    @DisplayName("Form boşken kayıt ol butonu disabled olmalı")
    @Severity(SeverityLevel.MINOR)
    @Story("Buton durumu")
    public void registerButtonDisabledWhenFormEmpty() {
        assertFalse(pages.registerPage().isRegisterButtonEnabled(),
                "Form boşken kayıt ol butonu disabled olmalı");
    }

    @Test
    @DisplayName("Geçerli bilgilerle kayıt olunca ilk bebeğini ekle sayfasına yönlendirilmeli")
    @Description("Ad, email, telefon ve şifre girilip kayıt olununca ilk bebeğini ekle ekranı görüntülenmeli")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Başarılı kayıt")
    public void successfulRegisterRedirectsToAddFirstBabyPage() {
        registeredEmail = TestData.generateEmail();
        registeredPhoneNumber= TestData.generatePhoneNumber();

        pages.registerPage().fillRegisterForm(
                TestData.REG_VALID_NAME,
                registeredEmail,
                registeredPhoneNumber,
                TestData.REG_VALID_PASSWORD
        );
        pages.registerPage().clickRegister();

        pages.registerPage().dismissSuccessDialog();
        pages.registerPage().dismissAutofillSavePopupIfPresent();

        assertTrue(pages.addFirstBabyPage().isDisplayed(),
                "Başarılı kayıt sonrası ilk bebeğini ekle sayfası görüntülenmeli");
    }

    @AfterEach
    public void cleanupIfNeeded() {
        if (registeredEmail != null) {
            AccountCleanupUtil.deleteTestAccount(
                    registeredEmail,
                    TestData.REG_VALID_PASSWORD
            );
            registeredEmail = null;
        }
    }
}