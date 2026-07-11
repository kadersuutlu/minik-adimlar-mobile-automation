package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import base.AppFlowManager;
import base.BaseTest;

public class PersonalInfoTest extends BaseTest {

	@BeforeEach
	public void setUpPage() {

		AppFlowManager flow = new AppFlowManager(driver,pages);

		flow.loginAndCleanStart("genctestmuhendis@gmail.com", "Test123");

		assertTrue(pages.homePage().isDisplayed(), "Onboarding sonrası ana sayfa yüklenemedi!");

		pages.homePage().clickProfileIcon();
		pages.profilePage().clickProfilePersonalInfoButton();
	}

	@Test
	public void testPersonalInfoPageIsDisplayed() {
		assertTrue(pages.personalInfoPage().isDisplayed(), "Kişisel Bilgiler sayfası görüntülenemedi!");
	}

	@Test
	public void testUpdatePersonalInfoSuccess() {
		pages.personalInfoPage().enterName("Yeni İsim");
		pages.personalInfoPage().enterEmail("yeniemail2@test.com");
		pages.personalInfoPage().enterPhone("5550009988");
		driver.hideKeyboard();
		pages.personalInfoPage().clickSave();

		assertEquals(pages.personalInfoPage().getAlertTitleText(), "E-posta Değişikliği");

		pages.personalInfoPage().clickAlertDevamEt();

		assertTrue(pages.loginPage().isDisplayed(), "Login ekranına yönlendirme");
	}

	public void testCancelEmailChange() {
		pages.personalInfoPage().enterEmail("iptal_testi@gmail.com");
		driver.hideKeyboard();
		pages.personalInfoPage().clickSave();

		assertEquals(pages.personalInfoPage().getAlertTitleText(), "E-posta Değişikliği");

		pages.personalInfoPage().clickAlertIptal();

		assertTrue(pages.profilePage().isDisplayed(), "İptal sonrası Personal Info sayfasından çıkıldı!");
	}

	@Test
	public void testInvalidEmailFormatError() {
		pages.personalInfoPage().enterEmail("gecersiz_email_formatı");
		pages.personalInfoPage().clickSave();

		String errorText = pages.personalInfoPage().getEmailErrorText();

		assertTrue(errorText.contains("Geçerli"), "Invalid email error message should be displayed");

	}

	@Test
	public void testCancelButtonFunctionality() {
		pages.personalInfoPage().enterName("Kaydedilmeyecek İsim");
		pages.personalInfoPage().clickCancel();

		assertTrue(pages.profilePage().isDisplayed());
	}

	@Test
	public void testNavigationBackToProfile() {
		pages.personalInfoPage().clickPrevious();
		assertTrue(pages.profilePage().isDisplayed(), "Geri butonuna basınca Profile sayfasına dönülmedi!");
	}
}