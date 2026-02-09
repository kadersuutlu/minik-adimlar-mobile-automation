package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import base.BaseTest;
import io.appium.java_client.AppiumBy;
import pages.LoginPage;
import pages.OnboardingFirstPage;
import pages.OnboardingSecondPage;
import pages.RegisterPage;

public class OnboardingTest extends BaseTest {

	private OnboardingFirstPage firstPage;
	private OnboardingSecondPage secondPage;

	@BeforeEach
	public void initPages() {
		firstPage = new OnboardingFirstPage(driver);
		secondPage = new OnboardingSecondPage(driver);
	}

	@Test
	public void shouldDisplayFirstOnboardingPage() {
		assertTrue(firstPage.isContinueButtonDisplayed(), "Onboarding First Page is not displayed");
	}

	@Test
	public void shouldNavigateToSecondPage_whenContinueTapped() {
		firstPage.tapContinue();

		assertTrue(secondPage.isRegisterButtonDisplayed(), "Register button is not visible on Second Page");

		assertTrue(secondPage.isLoginButtonDisplayed(), "Login button is not visible on Second Page");
	}

	@Test
	public void shouldNavigateToRegisterPage_whenRegisterTapped() {
		firstPage.tapContinue();
		secondPage.tapRegister();

		RegisterPage registerPage = new RegisterPage(driver);

		assertTrue(registerPage.isDisplayed(), "Register Page is not displayed");
	}

	@Test
	public void shouldNavigateToLoginPage_whenLoginTapped() {
		firstPage.tapContinue();
		secondPage.tapLogin();

		LoginPage loginPage = new LoginPage(driver);

		assertTrue(loginPage.isDisplayed(), "Login Page is not displayed");
	}
}