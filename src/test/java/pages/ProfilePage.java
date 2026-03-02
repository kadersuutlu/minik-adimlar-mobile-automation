package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class ProfilePage extends BasePage {

	public ProfilePage(AndroidDriver driver) {
		super(driver);
		System.out.println("ProfilePage initialized");
	}

	By profileTitle = AppiumBy.accessibilityId("profile_title");

	private By profilePersonalInfoButton = AppiumBy.accessibilityId("profile_personal_info_button");
	private By profileMyBabiesButton = AppiumBy.accessibilityId("profile_my_babies_button");
	private By profileReadingListButton = AppiumBy.accessibilityId("profile_reading_list_button");
	private By profileChangePasswordButton = AppiumBy.accessibilityId("profile_change_password_button");
	private By profileLogOutButton = AppiumBy.accessibilityId("profile_logout_button");
	private By profileUserAgreementText = AppiumBy.accessibilityId("profile_user_agreement_text");
	private By profilePrivacyPolicyText = AppiumBy.accessibilityId("profile_privacy_policy_text");

	public boolean isDisplayed() {
		return driver.findElements(profileTitle).size() > 0;
	}

	public void clickProfilePersonalInfoButton() {
		wait.until(ExpectedConditions.elementToBeClickable(profilePersonalInfoButton)).click();
	}

	public void clickProfileMyBabiesButton() {
		wait.until(ExpectedConditions.elementToBeClickable(profileMyBabiesButton)).click();
	}

	public void clickProfileReadingListButton() {
		wait.until(ExpectedConditions.elementToBeClickable(profileReadingListButton)).click();
	}

	public void clickProfileChangePasswordButton() {
		wait.until(ExpectedConditions.elementToBeClickable(profileChangePasswordButton)).click();
	}

	public void clickProfileLogOutButton() {
		wait.until(ExpectedConditions.elementToBeClickable(profileLogOutButton)).click();
	}
	
	public void clickProfileUserAgreementText() {
		wait.until(ExpectedConditions.elementToBeClickable(profileUserAgreementText)).click();
	}
	
	public void clickProfilePrivacyPolicyText() {
		wait.until(ExpectedConditions.elementToBeClickable(profilePrivacyPolicyText)).click();
	}
	
	public boolean areAllButtonsVisible() {
	    return driver.findElement(profilePersonalInfoButton).isDisplayed()
	            && driver.findElement(profileMyBabiesButton).isDisplayed()
	            && driver.findElement(profileReadingListButton).isDisplayed()
	            && driver.findElement(profileChangePasswordButton).isDisplayed()
	            && driver.findElement(profileLogOutButton).isDisplayed()
	            && driver.findElement(profileUserAgreementText).isDisplayed()
	            && driver.findElement(profilePrivacyPolicyText).isDisplayed();
	}
}