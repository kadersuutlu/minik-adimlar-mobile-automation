package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import base.AppFlowManager;
import base.BaseTest;
import pages.ForgotPasswordPage;
import pages.LoginPage;

public class ForgotPasswordTest extends BaseTest {

	private LoginPage loginPage;
	private ForgotPasswordPage forgotPasswordPage;

	@BeforeEach
	public void setup() {

		AppFlowManager flow = new AppFlowManager(driver);
		flow.goToLogin();

		loginPage = new LoginPage(driver);
		forgotPasswordPage = new ForgotPasswordPage(driver);

		loginPage.clickForgotPassword();

		Assert.assertTrue(forgotPasswordPage.isDisplayedForgotPasswordTitle(),
				"Login did not redirect to Forgot Password page");
	}

	@Test
	public void succesfullRedirectToSentEmailPage() {

		forgotPasswordPage.enterEmail("validuser@gmail.com");

		forgotPasswordPage.clickSendEmailButton();

		Assert.assertTrue(forgotPasswordPage.isDisplayedSentEmailTitle(),
				"Forgot Password did not redirect to Sent Email page");
	}

	@Test
	public void errorEmailFormat() {
		forgotPasswordPage.enterEmail("validuser");

		Assert.assertFalse(forgotPasswordPage.isSentEmailButtonEnabled(),
				"Sent Email button should be disabled when inputs are invalid");
	}

	@Test
	public void succesfullRedirectToLoginPageWhenClickGoToLoginButtonOnSentEmailPage() {
		
		forgotPasswordPage.enterEmail("validuser@gmail.com");

		forgotPasswordPage.clickSendEmailButton();
		
		forgotPasswordPage.clickResetLinkSentButton();
		
		Assert.assertTrue(loginPage.isDisplayed(),"Sent Email Page did not redirect to Login page");
	}

}
