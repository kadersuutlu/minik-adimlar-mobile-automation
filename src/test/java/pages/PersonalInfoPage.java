package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class PersonalInfoPage extends BasePage {

    public PersonalInfoPage(AndroidDriver driver) {
        super(driver);
        System.out.println("Kişisel Bilgilerim initialized");
    }

    private By personalInfoTitle = AppiumBy.accessibilityId("personal_info_title");
    private By personalInfoNameInput = AppiumBy.accessibilityId("personal_info_name_input");
    private By personalInfoEmailInput = AppiumBy.accessibilityId("personal_info_email_input");
    private By personalInfoPhoneInput = AppiumBy.accessibilityId("personal_info_phone_input");
    private By personalInfoSaveButton = AppiumBy.accessibilityId("personal_info_save_button");
    private By personalInfoCancelButton = AppiumBy.accessibilityId("personal_info_cancel_button");
    private By personalInfoNotificationButton = AppiumBy.accessibilityId("personal_info_notification_button");
    private By personalInfoPreviousButton = AppiumBy.accessibilityId("personal_info_previous_button");
    private By emailError = AppiumBy.accessibilityId("personal_info_email_input_error");

    private By alertTitle = By.id("com.juniors.minikadimlar:id/alert_title");
    private By alertDevamEtButton = By.id("android:id/button1");
    private By alertIptalButton = By.id("android:id/button2");

    @Step("Kişisel bilgilerim sayfasının görüntülendiği doğrulanıyor")
    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(personalInfoTitle)).isDisplayed();
    }

    @Step("İsim alanına '{name}' yazıldı")
    public void enterName(String name) {
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(personalInfoNameInput));
        nameField.clear();
        nameField.sendKeys(name);
    }

    @Step("E-posta alanına '{email}' yazıldı")
    public void enterEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(personalInfoEmailInput));
        emailField.clear();
        emailField.sendKeys(email);
    }

    @Step("Telefon alanına '{phone}' yazıldı")
    public void enterPhone(String phone) {
        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(personalInfoPhoneInput));
        phoneField.clear();
        phoneField.sendKeys(phone);
    }

    @Step("Kaydet butonuna tıklandı")
    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(personalInfoSaveButton)).click();
    }

    @Step("Vazgeç butonuna tıklandı")
    public void clickCancel() {
        wait.until(ExpectedConditions.elementToBeClickable(personalInfoCancelButton)).click();
    }

    @Step("Bildirim butonuna tıklandı")
    public void clickNotification() {
        wait.until(ExpectedConditions.elementToBeClickable(personalInfoNotificationButton)).click();
    }

    @Step("Geri butonuna tıklandı")
    public void clickPrevious() {
        wait.until(ExpectedConditions.elementToBeClickable(personalInfoPreviousButton)).click();
    }

    @Step("E-posta hata mesajı okunuyor")
    public String getEmailErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailError)).getText();
    }

    @Step("Alert başlığı okunuyor")
    public String getAlertTitleText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(alertTitle)).getText();
    }

    @Step("Alert'te 'Devam Et' butonuna tıklandı")
    public void clickAlertDevamEt() {
        wait.until(ExpectedConditions.elementToBeClickable(alertDevamEtButton)).click();
    }

    @Step("Alert'te 'İptal' butonuna tıklandı")
    public void clickAlertIptal() {
        wait.until(ExpectedConditions.elementToBeClickable(alertIptalButton)).click();
    }
}