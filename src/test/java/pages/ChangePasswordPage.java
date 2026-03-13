package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class ChangePasswordPage extends BasePage {

	public ChangePasswordPage(AndroidDriver driver) {
		super(driver);
		System.out.println("ChangePasswordPage initialized");
	}

	By changePasswordTitle = AppiumBy.accessibilityId("change_password_title");
	
	private By currentPasswordInput = AppiumBy.accessibilityId("change_password_current_input");
    private By newPasswordInput = AppiumBy.accessibilityId("change_password_new_input");
    private By confirmPasswordInput = AppiumBy.accessibilityId("change_password_confirm_input");
    private By currentPasswordVisibilityIcon = AppiumBy.id("change_password_current_input_visibility_icon");
    private By newPasswordVisibilityIcon = AppiumBy.id("change_password_new_input_visibility_icon");
    private By confirmPasswordVisibilityIcon = AppiumBy.id("change_password_confirm_input_visibility_icon");
    private By saveButton = AppiumBy.accessibilityId("change_password_save_button");
    private By cancelButton = AppiumBy.accessibilityId("change_password_cancel_button");
    private By forgotPasswordLink = AppiumBy.accessibilityId("change_password_forgot_password_text");
    private By mismatchError = AppiumBy.accessibilityId("change_password_error_mismatch");
    private By wrongCurrentPasswordError = AppiumBy.accessibilityId("change_password_error_wrong_current");
    
    private By successAlertTitle = By.id("com.juniors.minikadimlar:id/alert_title");
    private By successAlertMessage = By.id("android:id/message");
    private By successAlertOkButton = By.id("android:id/button1");

    public String getSuccessAlertMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successAlertMessage)).getText();
    }

    public void clickSuccessAlertOk() {
        driver.findElement(successAlertOkButton).click();
    }

    public boolean isDisplayed() {
        return driver.findElements(changePasswordTitle).size() > 0;
    }

    public void fillChangePasswordForm(String currentPw, String newPw, String confirmPw) {
        driver.findElement(currentPasswordInput).sendKeys(currentPw);
        driver.findElement(newPasswordInput).sendKeys(newPw);
        driver.findElement(confirmPasswordInput).sendKeys(confirmPw);
    }

    public void clickSave() {
        driver.findElement(saveButton).click();
    }

    public void clickCancel() {
        driver.findElement(cancelButton).click();
    }

    public void clickForgotPassword() {
        driver.findElement(forgotPasswordLink).click();
    }

    public boolean isMismatchErrorDisplayed() {
        return driver.findElements(mismatchError).size() > 0;
    }

    public boolean isWrongCurrentPasswordErrorDisplayed() {
        return driver.findElements(wrongCurrentPasswordError).size() > 0;
    }
    
    public String getWrongCurrentPasswordMessage() {
        return driver.findElement(wrongCurrentPasswordError).getText();
    }
}