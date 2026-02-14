package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import base.AppFlowManager;
import base.BaseTest;
import pages.ForgotPasswordPage;
import pages.AddFirstBabyPage;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;

public class LoginTest extends BaseTest {

	private LoginPage loginPage;
	private HomePage homePage;
	private AddFirstBabyPage addFirstBabyPage;

	@BeforeEach
	public void setupPage() {
		AppFlowManager flow = new AppFlowManager(driver);
		flow.goToLogin();
		loginPage = new LoginPage(driver);

	}

	@Test
	public void successfulLoginRedirectsToCorrectPageBasedOnUserState() {

		loginPage.enterEmail("validuser@gmail.com");
		loginPage.enterPassword("Valid123");
		driver.hideKeyboard();
		loginPage.clickLogin();

		addFirstBabyPage = new AddFirstBabyPage(driver);
		homePage = new HomePage(driver);

		boolean isAddFirstBabyVisible = addFirstBabyPage.isDisplayed();
		boolean isHomePageVisible = homePage.isDisplayed();

		Assert.assertTrue(isAddFirstBabyVisible || isHomePageVisible, "Login did not redirect to expected page");

	}

	@Test
	public void loginWithInvalidEmailShowsError() {

		loginPage.enterEmail("invalidEmail");
		loginPage.enterPassword("Valid123");

		Assert.assertTrue(loginPage.getEmailErrorText().length() > 0, "Email format error should be displayed");
	}

	@Test
	public void loginWithEmptyPasswordShowsError() {

		loginPage.enterEmail("test@gmail.com");
		loginPage.enterPassword("");
		loginPage.clickEmailField();
		driver.hideKeyboard();

		Assert.assertTrue(loginPage.getPasswordErrorText().length() > 0, "Password required error should be displayed");
	}

	@Test
	public void loginButtonDisabledWhenFieldsAreInvalid() {

		loginPage.enterEmail("invalidemail");
		loginPage.enterPassword("");
		driver.hideKeyboard();

		Assert.assertFalse(loginPage.isLoginButtonEnabled(), "Login button should be disabled when inputs are invalid");
	}

	@Test
	public void loginWithWrongPasswordShowsError() {

		loginPage.enterEmail("validuser@gmail.com");
		loginPage.enterPassword("Wrong123");
		driver.hideKeyboard();
		loginPage.clickLogin();

		String errorText = loginPage.getPasswordErrorText();

		Assert.assertTrue(errorText.contains("hatalı"), "Wrong password error message should be displayed");
	}

	@Test
	public void visibilityPasswordWhenClickVisibilityPasswordIcon() {

		String password = "Valid123";
		loginPage.enterPassword(password);
		driver.hideKeyboard();

		String initialText = loginPage.getPasswordText();
		Assert.assertNotEquals(initialText, password);

		loginPage.clickPasswordVisibility();

		String visibleText = loginPage.getPasswordText();
		Assert.assertEquals(visibleText, password);

		loginPage.clickPasswordVisibility();
		Assert.assertNotEquals(initialText, password);
	}

	@Test
	public void forgotPaswordRedirectToForgotPasswordPage() {

		loginPage.clickForgotPassword();

		ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);

		Assert.assertTrue(forgotPasswordPage.isDisplayedForgotPasswordTitle(),
				"Login did not redirect to Forgot Password page");
	}

	@Test
	public void goToSignUpPageWhenClickGoToSignUpText() {

		loginPage.clickGoToSignUpButton();

		RegisterPage registerPage = new RegisterPage(driver);

		Assert.assertTrue(registerPage.isDisplayed(), "Login did not redirect to Register page");
	}
}