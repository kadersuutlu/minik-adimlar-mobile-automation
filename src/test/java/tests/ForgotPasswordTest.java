package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import com.google.common.collect.ImmutableMap;

import api.ForgotPasswordApi;
import base.AppFlowManager;
import base.BaseTest;
import pages.AddFirstBabyPage;
import pages.ForgotPasswordPage;
import pages.HomePage;
import pages.LoginPage;

public class ForgotPasswordTest extends BaseTest {

	private LoginPage loginPage;
	private ForgotPasswordPage forgotPasswordPage;
	private AddFirstBabyPage addFirstBabyPage;
	private HomePage homePage;

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

		
		Assert.assertTrue(loginPage.isDisplayed(), "Sent Email Page did not redirect to Login page");
	}

	@Test
	public void successfulResetPasswordFlow() {

		String email = "validuser@gmail.com";

	    forgotPasswordPage.enterEmail(email);
	    forgotPasswordPage.clickSendEmailButton();

	    Assert.assertTrue(forgotPasswordPage.isDisplayedSentEmailTitle(),
				"Forgot Password did not redirect to Sent Email page");
	    
	    // API’den token al
	    String token = ForgotPasswordApi.getResetToken(email);

	    // Deep link ile reset password ekranını aç
	    driver.executeScript("mobile: deepLink", ImmutableMap.of(
	            "url", "minikadimlar://reset-password?token=" + token,
	            "package", "com.juniors.minikadimlar"
	    ));

	    Assert.assertTrue(forgotPasswordPage.isDisplayedCreateNewPasswordTitle());

	    forgotPasswordPage.createNewPassword("NewPass123!");

	    Assert.assertTrue(loginPage.isDisplayed());

	    loginPage.fillLoginForm("validuser@gmail.com", "NewPass123!");	    
	    driver.hideKeyboard();
		loginPage.clickLogin();

		addFirstBabyPage = new AddFirstBabyPage(driver);
		homePage = new HomePage(driver);

		boolean isAddFirstBabyVisible = addFirstBabyPage.isDisplayed();
		boolean isHomePageVisible = homePage.isDisplayed();

		Assert.assertTrue(isAddFirstBabyVisible || isHomePageVisible, "Login did not redirect to expected page");
	}

	
}