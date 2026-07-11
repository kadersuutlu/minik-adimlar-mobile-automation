package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import base.BaseTest;

public class OnboardingTest extends BaseTest {
	
	@BeforeEach
	public void setup() {
        driver.terminateApp("com.juniors.minikadimlar");
        driver.activateApp("com.juniors.minikadimlar");
		assertTrue(pages.firstPage().isContinueButtonDisplayed(),"Onboarding Page is not displayed");
	}

    @Test
    public void fullOnboardingNavigationFlow() {

        assertTrue(pages.firstPage().isContinueButtonDisplayed(), "Onboarding First Page is not displayed");


        pages.firstPage().tapContinue();
        assertTrue(pages.secondPage().isRegisterButtonDisplayed(), "Register button is not visible on Second Page");
        assertTrue(pages.secondPage().isLoginButtonDisplayed(), "Login button is not visible on Second Page");


        pages.secondPage().tapRegister();
        assertTrue(pages.registerPage().isDisplayed(), "Register Page is not displayed");
    }
}