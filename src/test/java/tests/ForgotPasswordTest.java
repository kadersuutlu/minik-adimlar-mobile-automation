package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import com.google.common.collect.ImmutableMap;

import api.ForgotPasswordApi;
import base.AppFlowManager;
import base.BaseTest;
import data.TestData;

public class ForgotPasswordTest extends BaseTest {

	@BeforeEach
	public void setup() {
		AppFlowManager flow = new AppFlowManager(driver);
		flow.goToLogin();

		pages.loginPage().clickForgotPassword();

		Assert.assertTrue(pages.forgotPasswordPage().isDisplayedForgotPasswordTitle(),
				"Login did not redirect to Forgot Password page");
	}

	@Test
	public void succesfullRedirectToSentEmailPage() {

		pages.forgotPasswordPage().enterEmail(TestData.FP_VALID_EMAIL);
		pages.forgotPasswordPage().clickSendEmailButton();

		Assert.assertTrue(pages.forgotPasswordPage().isDisplayedSentEmailTitle(),
				"Forgot Password did not redirect to Sent Email page");
	}

	@Test
	public void errorEmailFormat() {

		pages.forgotPasswordPage().enterEmail(TestData.FP_INVALID_EMAIL);

		Assert.assertFalse(pages.forgotPasswordPage().isSentEmailButtonEnabled(),
				"Send Email button should be disabled when inputs are invalid");
	}

	@Test
	public void succesfullRedirectToLoginPageWhenClickGoToLoginButtonOnSentEmailPage() {

		pages.forgotPasswordPage().enterEmail(TestData.FP_VALID_EMAIL);
		pages.forgotPasswordPage().clickSendEmailButton();
		pages.forgotPasswordPage().clickResetLinkSentButton();

		Assert.assertTrue(pages.loginPage().isDisplayed(), "Sent Email Page did not redirect to Login page");
	}

	
	//FAIL
	@Test
	public void successfulResetPasswordFlow() {

		String email = TestData.FP_VALID_EMAIL;

		// Send reset email
		pages.forgotPasswordPage().enterEmail(email);
		pages.forgotPasswordPage().clickSendEmailButton();

		Assert.assertTrue(pages.forgotPasswordPage().isDisplayedSentEmailTitle(),
				"Forgot Password did not redirect to Sent Email page");

		// Token API’den al
		String token = ForgotPasswordApi.getResetToken(email);

		// Deep link ile reset password ekranını aç
		driver.executeScript("mobile: deepLink", ImmutableMap.of("url", "minikadimlar://reset-password?token=" + token,
				"package", "com.juniors.minikadimlar"));
		
		//Uygulamaya Git butonuna tıkla
		pages.forgotPasswordPage().clickGoToAppButton();

		Assert.assertTrue(pages.forgotPasswordPage().isDisplayedCreateNewPasswordTitle());

		// TestData’daki yeni şifreyi kullan
		pages.forgotPasswordPage().createNewPassword(TestData.FP_NEW_PASSWORD);

		// Login sayfasına yönlendirildiğini doğrula
		Assert.assertTrue(pages.loginPage().isDisplayed());

		// Yeni şifre ile login ol
		pages.loginPage().fillLoginForm(TestData.FP_VALID_EMAIL, TestData.FP_NEW_PASSWORD);
		driver.hideKeyboard();
		pages.loginPage().clickLogin();

		boolean isAddFirstBabyVisible = pages.addFirstBabyPage().isDisplayed();
		boolean isHomePageVisible = pages.homePage().isDisplayed();

		Assert.assertTrue(isAddFirstBabyVisible || isHomePageVisible,
				"Login did not redirect to expected page after password reset");
	}
}