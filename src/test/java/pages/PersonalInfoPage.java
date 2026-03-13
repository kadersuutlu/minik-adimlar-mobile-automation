package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class PersonalInfoPage extends BasePage {

	public PersonalInfoPage(AndroidDriver driver) {
		super(driver);
		System.out.println("PersonalInfoPage initialized");
	}

	By personalInfoTitle = AppiumBy.accessibilityId("personal_info_title");
	private By personalInfoNameInput = AppiumBy.accessibilityId("personal_info_name_input");
	private By personalInfoEmailInput = AppiumBy.accessibilityId("personal_info_email_input");
	private By personalInfoPhoneInput = AppiumBy.accessibilityId("personal_info_phone_input");
	private By personalInfoSaveButton = AppiumBy.accessibilityId("personal_info_save_button");
	private By personalInfoCancelButton = AppiumBy.accessibilityId("personal_info_cancel_button");
	private By personalInfoNotificationButton = AppiumBy.accessibilityId(" ");
	private By personalInfoPreviousButton = AppiumBy.accessibilityId(" ");
	private By emailError = AppiumBy.accessibilityId("personal_info_email_input_error");
	
	private By alertTitle = By.id("com.juniors.minikadimlar:id/alert_title"); 
	private By alertDevamEtButton = By.id("android:id/button1"); 
	private By alertIptalButton = By.id("android:id/button2");

	public boolean isDisplayed() {
		return driver.findElements(personalInfoTitle).size() > 0;
	}

	public void enterName(String name) {
		driver.findElement(personalInfoNameInput).clear();
		driver.findElement(personalInfoNameInput).sendKeys(name);
		
	}

	public void enterEmail(String email) {
		driver.findElement(personalInfoEmailInput).clear();
		driver.findElement(personalInfoEmailInput).sendKeys(email);
	}

	public void enterPhone(String phone) {
		driver.findElement(personalInfoPhoneInput).clear();
		driver.findElement(personalInfoPhoneInput).sendKeys(phone);
	}

	public void clickSave() {
		driver.findElement(personalInfoSaveButton).click();
	}

	public void clickCancel() {
		driver.findElement(personalInfoCancelButton).click();
	}

	public void clickNotification() {
		driver.findElement(personalInfoNotificationButton).click();
	}

	public void clickPrevious() {
		driver.findElement(personalInfoPreviousButton).click();
	}
	
	public String getEmailErrorText() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(emailError)).getText();
	}
	
	public String getAlertTitleText() {
	    return driver.findElement(alertTitle).getText();
	}

	public void clickAlertDevamEt() {
	    driver.findElement(alertDevamEtButton).click();
	}

	public void clickAlertIptal() {
	    driver.findElement(alertIptalButton).click();
	}
}