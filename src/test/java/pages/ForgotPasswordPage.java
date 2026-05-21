package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class ForgotPasswordPage extends BasePage{

	public ForgotPasswordPage(AndroidDriver driver) {
		super(driver);
		System.out.println("Şifremi Unuttum initialized");
	}

	private By forgotPasswordTitle = AppiumBy.accessibilityId("forgot_password_title");

	private By emailField = AppiumBy.accessibilityId("forgot_password_email_input");

	private By sendLinkButton = AppiumBy.accessibilityId("forgot_password_send_link_button");

	private By sentEmailTitle = AppiumBy.accessibilityId("reset_link_sent_title");

	private By resetLinkSentButton = AppiumBy.accessibilityId("reset_link_sent_continue_button");

	private By createNewPasswordTitle = AppiumBy.accessibilityId("create_new_password_title");

	private By newPasswordField = AppiumBy.accessibilityId("create_new_password_input");

	private By newPasswordConfirmField = AppiumBy.accessibilityId("create_new_password_confirm_input");

	private By changePasswordButton = AppiumBy.accessibilityId("create_new_password_continue_button");

	private By passwordError = AppiumBy.xpath("//*[contains(@resource-id,'create_new_password_confirm_input_error')]");

	private By sessionTimeOut = By
			.xpath("//android.widget.TextView[@text=\"Oturum suresiz doldu. Lutfen tekrar giris yapin.\"]");
	
	private By goToAppButton=By.xpath("//android.widget.TextView[@text=\"Uygulamaya Git\"]");

	public boolean isDisplayedForgotPasswordTitle() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(forgotPasswordTitle)).isDisplayed();
	}

	public void enterEmail(String email) {
		driver.findElement(emailField).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
	}

	public void clickSendEmailButton() {
		wait.until(ExpectedConditions.elementToBeClickable(sendLinkButton)).click();
	}
	
	public boolean isSentEmailButtonEnabled() {
		return driver.findElement(sendLinkButton).isEnabled();
	}
	
	public boolean isDisplayedSentEmailTitle() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(sentEmailTitle)).isDisplayed();
	}
	
	public void clickResetLinkSentButton() {
		wait.until(ExpectedConditions.elementToBeClickable(resetLinkSentButton)).click();
	}
	
	public boolean isDisplayedCreateNewPasswordTitle() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(createNewPasswordTitle)).isDisplayed();
	}
	
	public void enterNewPassword(String newPassword) {
		driver.findElement(newPasswordField).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(newPasswordField)).sendKeys(newPassword);
	}
	
	public void enterNewPasswordConfirm(String newPasswordConfirm) {
		driver.findElement(newPasswordConfirmField).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(newPasswordConfirmField)).sendKeys(newPasswordConfirm);
	}
	
	public void clickChangePasswordButton() {
		wait.until(ExpectedConditions.elementToBeClickable(changePasswordButton)).click();
	}
	
	public String getPasswordErrorText() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError)).getText();
	}

	public String getTimeOutErrorText() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(sessionTimeOut)).getText();
	}
	
	public void createNewPassword(String password) {
	    enterNewPassword(password);
	    enterNewPasswordConfirm(password);
	    clickChangePasswordButton();
	}
	
	public void clickGoToAppButton() {
		wait.until(ExpectedConditions.elementToBeClickable(goToAppButton)).click();
	}
}