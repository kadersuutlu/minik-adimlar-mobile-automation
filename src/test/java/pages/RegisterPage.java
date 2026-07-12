package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class RegisterPage extends BasePage {

    public RegisterPage(AndroidDriver driver) {
        super(driver);
        System.out.println("Kayıt Ol initialized");
    }

    private By registerHeader = AppiumBy.accessibilityId("signup_title");

    private By nameField = AppiumBy.accessibilityId("signup_name_input");
    private By emailField = AppiumBy.accessibilityId("signup_email_input");
    private By phoneField = AppiumBy.accessibilityId("signup_phone_input");
    private By passwordField = AppiumBy.accessibilityId("signup_password_input");
    private By registerButton = AppiumBy.accessibilityId("signup_continue_button");
    private By goToSignInButton = AppiumBy.accessibilityId("signup_go_to_signin_button");

    private By kvkkText = AppiumBy.accessibilityId("signup_kvkk_checkbox");
    private By privacyPolicyText = AppiumBy.accessibilityId("signup_privacy_policy_checkbox");

    private By emailError = By.xpath("//android.widget.TextView[@text='Lütfen geçerli bir email adresi girin.']");
    private By passwordError = By.xpath("//android.widget.TextView[@text='Şifre en az 6 karakter olmalıdır.']");
    private By passwordUppercaseError = By.xpath("//android.widget.TextView[@text='Şifre en az 1 büyük harf içermelidir.']");
    private By passwordLowercaseError = By.xpath("//android.widget.TextView[@text='Şifre en az 1 küçük harf içermelidir.']");
    private By passwordEmptyError = By.xpath("//android.widget.TextView[@text='Şifre zorunludur.']");
    private By passwordSpecialCharError = By.xpath("//android.widget.TextView[@text='Şifre en az 1 özel karakter içermelidir.']");

    private By successDialogContinueButton = By.id("android:id/button1");

    @Step("Kayıt sayfasının görüntülendiği doğrulanıyor")
    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(registerHeader)).isDisplayed();
    }

    @Step("İsim girildi: {name}")
    public void enterName(String name) {
        clickAndSendKeys(nameField, name);
    }

    @Step("Email girildi: {email}")
    public void enterEmail(String email) {
        clickAndSendKeys(emailField, email);
    }

    @Step("Telefon girildi: {phone}")
    public void enterPhone(String phone) {
        clickAndSendKeys(phoneField, phone);
    }

    @Step("Şifre girildi")
    public void enterPassword(String password) {
        clickAndSendKeys(passwordField, password);
    }

    @Step("Kayıt ol butonuna tıklandı")
    public void clickRegister() {
        click(registerButton);
    }

    @Step("Kayıt formu dolduruldu")
    public void fillRegisterForm(String name, String email, String phone, String password) {
        enterName(name);
        enterEmail(email);
        enterPhone(phone);
        driver.hideKeyboard();
        enterPassword(password);
        driver.hideKeyboard();
    }

    @Step("Kayıt ol butonu aktif mi kontrol ediliyor")
    public boolean isRegisterButtonEnabled() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(registerButton)).isEnabled();
    }

    @Step("Email hata mesajı alındı")
    public String getEmailErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailError)).getText();
    }

    @Step("Şifre boş hata mesajı alındı")
    public String getPasswordEmptyErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordEmptyError)).getText();
    }

    @Step("Şifre minimum uzunluk hata mesajı alındı")
    public String getPasswordMinLengthErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError)).getText();
    }

    @Step("Şifre büyük harf hata mesajı alındı")
    public String getPasswordUppercaseErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordUppercaseError)).getText();
    }

    @Step("Şifre küçük harf hata mesajı alındı")
    public String getPasswordLowercaseErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordLowercaseError)).getText();
    }

    @Step("Şifre özel karakter hata mesajı alındı")
    public String getPasswordSpecialCharErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordSpecialCharError)).getText();
    }

    @Step("Kayıt başarılı popup'ı kapatıldı")
    public void dismissSuccessDialog() {
        click(successDialogContinueButton);
    }
}