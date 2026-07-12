package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import base.AppFlowManager;
import base.BaseTest;
import data.TestData;

import static org.junit.jupiter.api.Assertions.*;

@Feature("Giriş Yap")
public class LoginTest extends BaseTest {

    @BeforeEach
    public void setupPage() {
        resetApp();
        flow.goToLogin();
    }

    @Test
    @DisplayName("Bebek eklenmiş kullanıcı girişte ana sayfaya yönlendirilmeli")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Başarılı giriş")
    public void loginWithBabyUser_redirectsToHomePage() {
        pages.loginPage().fillLoginForm(
                TestData.LOG_USER_WITH_BABY_EMAIL,
                TestData.LOG_USER_WITH_BABY_PASSWORD
        );
        driver.hideKeyboard();
        pages.loginPage().clickLogin();

        flow.passMainOnboarding();

        assertTrue(pages.homePage().isDisplayed(), "Bebekli kullanıcı ana sayfaya yönlendirilmeli");
    }

    @Test
    @DisplayName("Bebek eklenmemiş kullanıcı girişte ilk bebeğini ekle sayfasına yönlendirilmeli")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Başarılı giriş")
    public void loginWithoutBabyUser_redirectsToAddFirstBabyPage() {
        pages.loginPage().fillLoginForm(
                TestData.LOG_USER_WITHOUT_BABY_EMAIL,
                TestData.LOG_USER_WITHOUT_BABY_PASSWORD
        );
        driver.hideKeyboard();
        pages.loginPage().clickLogin();

        assertTrue(pages.addFirstBabyPage().isDisplayed(), "Bebeksiz kullanıcı ilk bebeğini ekle sayfasına yönlendirilmeli");
    }

    @Test
    @DisplayName("Geçersiz email formatıyla giriş yapınca hata mesajı gösterilmeli")
    @Severity(SeverityLevel.NORMAL)
    @Story("Email validasyonu")
    public void loginWithInvalidEmailFormatShowsError() {
        pages.loginPage().enterEmail(TestData.LOG_INVALID_EMAIL_FORMAT);
        driver.hideKeyboard();

        String errorText = pages.loginPage().getEmailFormatErrorText();
        assertEquals("Geçersiz e-mail adresi.", errorText,
                "Geçersiz email format hata mesajı görüntülenmeli");
    }

    @Test
    @DisplayName("Kayıtlı olmayan email ile giriş yapınca hata dialog'u gösterilmeli")
    @Severity(SeverityLevel.NORMAL)
    @Story("Email validasyonu")
    public void loginWithUnregisteredEmailShowsError() {
        pages.loginPage().fillLoginForm(
                TestData.LOG_UNREGISTERED_EMAIL,
                TestData.LOG_USER_WITH_BABY_PASSWORD
        );
        driver.hideKeyboard();
        pages.loginPage().clickLogin();

        String dialogTitle = pages.loginPage().getErrorDialogTitle();
        assertEquals("Giriş Başarısız", dialogTitle,
                "Kayıtlı olmayan email ile giriş yapınca hata dialog'u görüntülenmeli");

        pages.loginPage().dismissErrorDialog();
    }

    @Test
    @DisplayName("Boş şifre girildiğinde giriş butonu pasif olmalı")
    @Severity(SeverityLevel.NORMAL)
    @Story("Şifre validasyonu")
    public void loginButtonDisabledWhenPasswordEmpty() {
        pages.loginPage().enterEmail(TestData.LOG_USER_WITH_BABY_EMAIL);
        pages.loginPage().enterPassword(TestData.LOG_EMPTY_PASSWORD);
        driver.hideKeyboard();

        assertFalse(pages.loginPage().isLoginButtonEnabled(),
                "Şifre boşken giriş butonu pasif olmalı");
    }

    @Test
    @DisplayName("Yanlış şifre ile giriş yapınca hata dialog'u gösterilmeli")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Şifre validasyonu")
    public void loginWithWrongPasswordShowsError() {
        pages.loginPage().fillLoginForm(
                TestData.LOG_USER_WITH_BABY_EMAIL,
                TestData.LOG_WRONG_PASSWORD
        );
        driver.hideKeyboard();
        pages.loginPage().clickLogin();

        String dialogTitle = pages.loginPage().getErrorDialogTitle();
        assertEquals("Giriş Başarısız", dialogTitle,
                "Yanlış şifre ile giriş yapınca hata dialog'u görüntülenmeli");

        pages.loginPage().dismissErrorDialog();
    }

    @Test
    @DisplayName("Geçersiz bilgilerle login butonu disabled olmalı")
    @Severity(SeverityLevel.MINOR)
    @Story("Buton durumu")
    public void loginButtonDisabledWhenFieldsAreInvalid() {
        pages.loginPage().enterEmail(TestData.LOG_INVALID_EMAIL_FORMAT);
        pages.loginPage().enterPassword(TestData.LOG_EMPTY_PASSWORD);
        driver.hideKeyboard();

        assertFalse(pages.loginPage().isLoginButtonEnabled(),
                "Geçersiz bilgilerle login butonu disabled olmalı");
    }

    @Test
    @DisplayName("Şifre görünürlük ikonu ile şifre gösterilip gizlenebilmeli")
    @Severity(SeverityLevel.MINOR)
    @Story("Şifre görünürlüğü")
    public void passwordVisibilityToggleWorksCorrectly() {
        pages.loginPage().enterPassword(TestData.LOG_USER_WITH_BABY_PASSWORD);
        driver.hideKeyboard();

        String hiddenText = pages.loginPage().getPasswordFieldText();
        assertNotEquals(hiddenText, TestData.LOG_USER_WITH_BABY_PASSWORD,
                "Şifre başlangıçta gizli olmalı");

        pages.loginPage().clickPasswordVisibility();
        String visibleText = pages.loginPage().getPasswordFieldText();
        assertEquals(visibleText, TestData.LOG_USER_WITH_BABY_PASSWORD,
                "İkon tıklanınca şifre görünür olmalı");

        pages.loginPage().clickPasswordVisibility();
        String hiddenAgain = pages.loginPage().getPasswordFieldText();
        assertNotEquals(hiddenAgain, TestData.LOG_USER_WITH_BABY_PASSWORD,
                "İkon tekrar tıklanınca şifre gizlenmeli");
    }

    @Test
    @DisplayName("Şifremi unuttum tıklanınca şifremi unuttum sayfasına gidilmeli")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigasyon")
    public void forgotPasswordNavigatesToForgotPasswordPage() {
        pages.loginPage().clickForgotPassword();

        assertTrue(pages.forgotPasswordPage().isDisplayedForgotPasswordTitle(),
                "Şifremi unuttum sayfası görüntülenmeli");
    }

    @Test
    @DisplayName("Kayıt ol tıklanınca kayıt sayfasına gidilmeli")
    @Severity(SeverityLevel.MINOR)
    @Story("Navigasyon")
    public void goToSignUpNavigatesToRegisterPage() {
        pages.loginPage().clickGoToSignUp();

        assertTrue(pages.registerPage().isDisplayed(),
                "Kayıt ol sayfası görüntülenmeli");
    }
}