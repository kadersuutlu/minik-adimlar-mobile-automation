package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class ProfilePage extends BasePage {

	public ProfilePage(AndroidDriver driver) {
		super(driver);
		System.out.println("Profil initialized");
	}

    private By profileTitle = AppiumBy.accessibilityId("profile_title");

    private By profilePersonalInfoButton = AppiumBy.accessibilityId("profile_personal_info_button");
    private By profileMyBabiesButton = AppiumBy.accessibilityId("profile_my_babies_button");
    private By profileReadingListButton = AppiumBy.accessibilityId("profile_reading_list_button");
    private By profileChangePasswordButton = AppiumBy.accessibilityId("profile_change_password_button");
    private By profileLogOutButton = AppiumBy.accessibilityId("profile_logout_button");
    private By profileDeleteAccountButton = AppiumBy.accessibilityId("profile_delete_account_button");

    private By profilePrivacyPolicyText = AppiumBy.xpath("//android.widget.TextView[@text='Gizlilik Politikası']");
    private By profileUserAgreementText = AppiumBy.xpath("//android.widget.TextView[@text='Kullanım Koşulları']");

    private By logOutConfirmTitle = AppiumBy.accessibilityId("logout_confirm_title");
    private By logOutConfirmLogOutButton = AppiumBy.accessibilityId("logout_confirm_logout_button");
    private By logOutConfirmCancelButton = AppiumBy.accessibilityId("logout_confirm_cancel_button");

    private By deleteAccountConfirmTitle = AppiumBy.accessibilityId("delete_account_confirm_title");
    private By deleteAccountConfirmButton = AppiumBy.accessibilityId("delete_account_confirm_button");
    private By deleteAccountCancelButton = AppiumBy.accessibilityId("delete_account_cancel_button");

    @Step("Profil sayfasının görüntülendiği doğrulanıyor")
    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(profileTitle)).isDisplayed();
    }

    @Step("Çıkış onay pop-up'ının görüntülendiği doğrulanıyor")
    public boolean isLogOutConfirmDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(logOutConfirmTitle)).isDisplayed();
    }

    @Step("Çıkış onay pop-up'ında 'Çıkış Yap' butonuna tıklandı")
    public void clickLogOutConfirmLogOutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logOutConfirmLogOutButton)).click();
    }

    @Step("Çıkış onay pop-up'ında 'Vazgeç' butonuna tıklandı")
    public void clickLogOutConfirmCancelButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logOutConfirmCancelButton)).click();
    }

    @Step("Kişisel bilgilerim butonuna tıklandı")
    public void clickProfilePersonalInfoButton() {
        wait.until(ExpectedConditions.elementToBeClickable(profilePersonalInfoButton)).click();
    }

    @Step("Bebeklerim butonuna tıklandı")
    public void clickProfileMyBabiesButton() {
        wait.until(ExpectedConditions.elementToBeClickable(profileMyBabiesButton)).click();
    }

    @Step("Okuma listesi butonuna tıklandı")
    public void clickProfileReadingListButton() {
        wait.until(ExpectedConditions.elementToBeClickable(profileReadingListButton)).click();
    }

    @Step("Şifre değiştir butonuna tıklandı")
    public void clickProfileChangePasswordButton() {
        wait.until(ExpectedConditions.elementToBeClickable(profileChangePasswordButton)).click();
    }

    @Step("Çıkış yap butonuna tıklandı")
    public void clickProfileLogOutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(profileLogOutButton)).click();
    }

    @Step("Hesabımı sil butonuna tıklandı")
    public void clickProfileDeleteAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(profileDeleteAccountButton)).click();
    }

    @Step("Profil ekranındaki tüm butonların görünür olduğu doğrulanıyor")
    public boolean areAllButtonsVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(profilePersonalInfoButton)).isDisplayed()
                && driver.findElement(profileMyBabiesButton).isDisplayed()
                && driver.findElement(profileReadingListButton).isDisplayed()
                && driver.findElement(profileChangePasswordButton).isDisplayed()
                && driver.findElement(profileLogOutButton).isDisplayed()
                && driver.findElement(profileDeleteAccountButton).isDisplayed()
                && driver.findElement(profileUserAgreementText).isDisplayed()
                && driver.findElement(profilePrivacyPolicyText).isDisplayed();
    }

    @Step("Hesap silme onay pop-up'ının görüntülendiği doğrulanıyor")
    public boolean isDeleteAccountConfirmDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(deleteAccountConfirmTitle)).isDisplayed();
    }

    @Step("Hesap silme onay pop-up'ında 'Hesabımı Sil' butonuna tıklandı")
    public void clickDeleteAccountConfirmButton() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteAccountConfirmButton)).click();
    }

    @Step("Hesap silme onay pop-up'ında 'Vazgeç' butonuna tıklandı")
    public void clickDeleteAccountCancelButton() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteAccountCancelButton)).click();
    }
}