package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import base.AppFlowManager;
import base.BaseTest;
import data.TestData;

import static org.junit.jupiter.api.Assertions.*;

@Feature("Giriş Yap")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest extends BaseTest {

    @BeforeEach
    public void setupPage() {
        AppFlowManager flow = new AppFlowManager(driver,pages);
        flow.goToLogin();
    }


    @Test
    @Order(1)
    @Disabled("BUG: Kullanıcı durumuna göre yönlendirme tutarsız, tekrar bakılacak")
    @DisplayName("Geçerli bilgilerle giriş yapınca doğru sayfaya yönlendirilmeli")
    @Description("Bebek eklenmiş kullanıcı → Ana sayfa, eklenmemiş → Bebek ekle sayfası")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Başarılı giriş")
    public void successfulLoginRedirectsToCorrectPage() {
        pages.loginPage().fillLoginForm(
                TestData.LOG_VALID_EMAIL,
                TestData.LOG_VALID_PASSWORD
        );
        driver.hideKeyboard();
        pages.loginPage().clickLogin();

        boolean isAddFirstBabyVisible = pages.addFirstBabyPage().isDisplayed();
        boolean isHomePageVisible = pages.homePage().isDisplayed();

        assertTrue(isAddFirstBabyVisible || isHomePageVisible,
                "Giriş sonrası beklenen sayfaya yönlendirilmedi");
    }


    @Test
    @Order(2)
    @DisplayName("Geçersiz email formatıyla giriş yapınca hata mesajı gösterilmeli")
    @Severity(SeverityLevel.NORMAL)
    @Story("Email validasyonu")
    public void loginWithInvalidEmailFormatShowsError() {
        pages.loginPage().enterEmail(TestData.LOG_INVALID_EMAIL_FORMAT);
        pages.loginPage().enterPassword(TestData.LOG_WRONG_PASSWORD);
        driver.hideKeyboard();

        String errorText = pages.loginPage().getEmailErrorText();
        assertFalse(errorText.isEmpty(), "Geçersiz email hata mesajı görüntülenmeli");
    }

    @Test
    @Order(3)
    @DisplayName("Kayıtlı olmayan email ile giriş yapınca hata dialog'u gösterilmeli")
    @Severity(SeverityLevel.NORMAL)
    @Story("Email validasyonu")
    public void loginWithUnregisteredEmailShowsError() {
        pages.loginPage().fillLoginForm(
                TestData.LOG_UNREGISTERED_EMAIL,
                TestData.LOG_VALID_PASSWORD
        );
        driver.hideKeyboard();
        pages.loginPage().clickLogin();

        String dialogTitle = pages.loginPage().getErrorDialogTitle();
        assertEquals("Giriş Başarısız", dialogTitle,
                "Kayıtlı olmayan email ile giriş yapınca hata dialog'u görüntülenmeli");

        pages.loginPage().dismissErrorDialog();
    }


    @Test
    @Order(4)
    @DisplayName("Boş şifre ile giriş yapınca hata mesajı gösterilmeli")
    @Severity(SeverityLevel.NORMAL)
    @Story("Şifre validasyonu")
    public void loginWithEmptyPasswordShowsError() {
        pages.loginPage().enterEmail(TestData.LOG_VALID_EMAIL);
        pages.loginPage().enterPassword(TestData.LOG_EMPTY_PASSWORD);
        driver.hideKeyboard();
        pages.loginPage().clickLogin();

        String errorText = pages.loginPage().getPasswordErrorText();
        assertFalse(errorText.isEmpty(), "Boş şifre hata mesajı görüntülenmeli");
    }

    @Test
    @Order(5)
    @DisplayName("Yanlış şifre ile giriş yapınca hata dialog'u gösterilmeli")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Şifre validasyonu")
    public void loginWithWrongPasswordShowsError() {
        pages.loginPage().fillLoginForm(
                TestData.LOG_VALID_EMAIL,
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
    @Order(6)
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
    @Order(7)
    @DisplayName("Şifre görünürlük ikonu ile şifre gösterilip gizlenebilmeli")
    @Severity(SeverityLevel.MINOR)
    @Story("Şifre görünürlüğü")
    public void passwordVisibilityToggleWorksCorrectly() {
        pages.loginPage().enterPassword(TestData.LOG_VALID_PASSWORD);
        driver.hideKeyboard();

        String hiddenText = pages.loginPage().getPasswordFieldText();
        assertNotEquals(hiddenText, TestData.LOG_VALID_PASSWORD,
                "Şifre başlangıçta gizli olmalı");

        pages.loginPage().clickPasswordVisibility();
        String visibleText = pages.loginPage().getPasswordFieldText();
        assertEquals(visibleText, TestData.LOG_VALID_PASSWORD,
                "İkon tıklanınca şifre görünür olmalı");

        pages.loginPage().clickPasswordVisibility();
        String hiddenAgain = pages.loginPage().getPasswordFieldText();
        assertNotEquals(hiddenAgain, TestData.LOG_VALID_PASSWORD,
                "İkon tekrar tıklanınca şifre gizlenmeli");
    }


    @Test
    @Order(8)
    @DisplayName("Şifremi unuttum tıklanınca şifremi unuttum sayfasına gidilmeli")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigasyon")
    public void forgotPasswordNavigatesToForgotPasswordPage() {
        pages.loginPage().clickForgotPassword();

        assertTrue(pages.forgotPasswordPage().isDisplayedForgotPasswordTitle(),
                "Şifremi unuttum sayfası görüntülenmeli");
    }

    @Test
    @Order(9)
    @DisplayName("Kayıt ol tıklanınca kayıt sayfasına gidilmeli")
    @Severity(SeverityLevel.MINOR)
    @Story("Navigasyon")
    public void goToSignUpNavigatesToRegisterPage() {
        pages.loginPage().clickGoToSignUp();

        assertTrue(pages.registerPage().isDisplayed(),
                "Kayıt ol sayfası görüntülenmeli");
    }
}