package tests;

import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeMethod;

import static org.junit.jupiter.api.Assertions.assertTrue;
import base.BaseTest;

public class OnboardingTest extends BaseTest {
	
	@BeforeMethod
	public void setup() {
		assertTrue(pages.firstPage().isContinueButtonDisplayed(),"Onboarding First Page is not displayed");
	}

	@Test
	public void shouldNavigateToSecondPage_whenContinueTapped() {
		pages.firstPage().tapContinue();

		assertTrue(pages.secondPage().isRegisterButtonDisplayed(), "Register button is not visible on Second Page");

		assertTrue(pages.secondPage().isLoginButtonDisplayed(), "Login button is not visible on Second Page");
	}

	@Test
	public void shouldNavigateToRegisterPage_whenRegisterTapped() {
		pages.firstPage().tapContinue();
		pages.secondPage().tapRegister();

		assertTrue(pages.registerPage().isDisplayed(), "Register Page is not displayed");
	}

	@Test
	public void shouldNavigateToLoginPage_whenLoginTapped() {
		pages.firstPage().tapContinue();
		pages.secondPage().tapLogin();

		assertTrue(pages.loginPage().isDisplayed(), "Login Page is not displayed");
	}
}