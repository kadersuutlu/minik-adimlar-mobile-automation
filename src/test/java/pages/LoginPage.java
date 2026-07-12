package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class LoginPage extends BasePage {

	public LoginPage(AndroidDriver driver) {
		super(driver);
		System.out.println("Giriş Yap initialized");
	}

    private By loginHeader = AppiumBy.accessibilityId("signin_title");

    private By emailField = AppiumBy.accessibilityId("signin_email_input");
    private By passwordField = AppiumBy.accessibilityId("signin_password_input");
    private By loginButton = AppiumBy.accessibilityId("signin_continue_button");
    private By goToSignUpButton = AppiumBy.accessibilityId("signin_go_to_signup_button");

    private By passwordVisibilityIcon = AppiumBy
            .xpath("//*[contains(@resource-id,'signin_password_input_visibility_icon')]");

    private By forgotPasswordText = AppiumBy.accessibilityId("signin_forgot_password_text");

    private final By errorDialogTitle = By.id("com.juniors.minikadimlar:id/alert_title");
    private final By errorDialogOkButton = By.id("android:id/button1");

    private By emailFormatError = By.xpath("//android.widget.TextView[@text='Geçersiz e-mail adresi.']");

    @Step("Login sayfasının görüntülendiği doğrulanıyor")
    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeader)).isDisplayed();
    }

    @Step("Email girildi: {email}")
    public void enterEmail(String email) {
        clickAndSendKeys(emailField, email);
    }

    @Step("Şifre girildi")
    public void enterPassword(String password) {
        clickAndSendKeys(passwordField, password);
    }

    @Step("Giriş yap butonuna tıklandı")
    public void clickLogin() {
        click(loginButton);
    }

    @Step("Şifremi unuttum butonuna tıklandı")
    public void clickForgotPassword() {
        click(forgotPasswordText);
    }

    @Step("Şifre görünürlük ikonuna tıklandı")
    public void clickPasswordVisibility() {
        click(passwordVisibilityIcon);
    }

    @Step("Kayıt ol sayfasına git butonuna tıklandı")
    public void clickGoToSignUp() {
        click(goToSignUpButton);
    }

    @Step("Login formu dolduruldu")
    public void fillLoginForm(String email, String password) {
        enterEmail(email);
        enterPassword(password);
    }

    @Step("Login butonu aktif mi kontrol ediliyor")
    public boolean isLoginButtonEnabled() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(loginButton)).isEnabled();
    }

    @Step("Şifre field metni alındı")
    public String getPasswordFieldText() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(passwordField)).getText();
    }

    @Step("Email format hata mesajı alındı")
    public String getEmailFormatErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailFormatError)).getText();
    }

    @Step("Hata dialog başlığı alındı")
    public String getErrorDialogTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorDialogTitle)).getText();
    }

    @Step("Hata dialog'u kapatıldı")
    public void dismissErrorDialog() {
        click(errorDialogOkButton);
    }
}