package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import base.AppFlowManager;
import base.BaseTest;

public class ProfileTest extends BaseTest{
	
	@BeforeEach
	public void setUpPage() {
		
		AppFlowManager flow = new AppFlowManager(driver);
        flow.goToLogin();
        pages.loginPage().fillLoginForm("validuser33@gmail.com", "Valid1234");
        driver.hideKeyboard();
        pages.loginPage().clickLogin();

	    pages.homePage().clickProfileIcon();

	}
	
	@Test
    void profileScreenShouldBeDisplayed() {
        assertTrue(pages.profilePage().isDisplayed());
    }

    @Test
    void allProfileButtonsShouldBeVisible() {
        assertTrue(pages.profilePage().areAllButtonsVisible());
    }

    @Test
    void shouldNavigateToPersonalInfoPage() {
        pages.profilePage().clickProfilePersonalInfoButton();
        assertTrue(pages.personalInfoPage().isDisplayed());
    }

    @Test
    void shouldNavigateToMyBabiesPage() {
        pages.profilePage().clickProfileMyBabiesButton();
        assertTrue(pages.myBabiesPage().isDisplayed());
    }

    @Test
    void shouldNavigateToReadingListPage() {
        pages.profilePage().clickProfileReadingListButton();
        assertTrue(pages.readingListPage().isDisplayed());
    }

    @Test
    void shouldNavigateToChangePasswordPage() {
        pages.profilePage().clickProfileChangePasswordButton();
        assertTrue(pages.changePasswordPage().isDisplayed());
    }

    @Test
    void shouldNavigateToUserAgreementPage() {
        pages.profilePage().clickProfileUserAgreementText();
        assertTrue(pages.userAgreementPage().isDisplayed());
    }

    @Test
    void shouldNavigateToPrivacyPolicyPage() {
        pages.profilePage().clickProfilePrivacyPolicyText();
        assertTrue(pages.privacyPolicyPage().isDisplayed());
    }
}