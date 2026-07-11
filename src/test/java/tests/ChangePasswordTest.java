package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import base.AppFlowManager;
import base.BaseTest;

public class ChangePasswordTest extends BaseTest {

	@BeforeEach
	public void setUpPage() {

		AppFlowManager flow = new AppFlowManager(driver,pages);
		flow.goToLogin();
		pages.loginPage().fillLoginForm("genctestmuhendis@gmail.com", "Test123");
		driver.hideKeyboard();
		pages.loginPage().clickLogin();

		pages.homePage().clickProfileIcon();
		pages.profilePage().clickProfileChangePasswordButton();

	}

	@Test
	public void profileScreenShouldBeDisplayed() {
		assertTrue(pages.changePasswordPage().isDisplayed());
	}

	@Test
	public void navigateToForgotPasswordWhenClickForgotPasswordText() {
		pages.changePasswordPage().clickForgotPassword();
		assertTrue(pages.forgotPasswordPage().isDisplayedForgotPasswordTitle());
	}

	@Test
	public void newPasswordsNotMatched() {
		pages.changePasswordPage().fillChangePasswordForm("Valid1234", "NewPass123", "DifferentPass123");
		driver.hideKeyboard();
		pages.changePasswordPage().clickSave();

		assertTrue(pages.changePasswordPage().isMismatchErrorDisplayed(), "Şifre uyuşmazlık hatası görüntülenemedi!");
	}

	@Test
	public void oldPasswordNotTrue() {
		pages.changePasswordPage().fillChangePasswordForm("WrongOldPass123", "NewPass123", "NewPass123");
		driver.hideKeyboard();
		pages.changePasswordPage().clickSave();

		assertTrue(pages.changePasswordPage().isWrongCurrentPasswordErrorDisplayed(),
				"Yanlış mevcut şifre hatası görüntülenemedi!");
		assertEquals(pages.changePasswordPage().getWrongCurrentPasswordMessage(), "Mevcut şifrenizi doğru girmediniz.");
	}

	@Test
	public void successfulChangePassword() {
		pages.changePasswordPage().fillChangePasswordForm("Valid1234", "Valid1234", "Valid12345");
		driver.hideKeyboard();
		pages.changePasswordPage().clickSave();

		String actualMessage = pages.changePasswordPage().getSuccessAlertMessage();
		assertEquals(actualMessage, "Şifreniz başarıyla değiştirildi!", "Başarı mesajı hatalı!");

		pages.changePasswordPage().clickSuccessAlertOk();

		assertTrue(pages.profilePage().isDisplayed(), "Başarı sonrası Profil ekranına dönülmedi!");
	}

	@Test
	public void cancelChangePassword() {
		pages.changePasswordPage().fillChangePasswordForm("Valid1234", "Valid1234", "Valid1234");
		driver.hideKeyboard();
		pages.changePasswordPage().clickCancel();
		assertTrue(pages.profilePage().isDisplayed(), "Vazgeçme sonrası Profil ekranına dönülmedi!");
	}
}