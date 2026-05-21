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
        
        flow.loginAndCleanStart("yeniemail2@test.com", "Valid1234");

        assertTrue(pages.homePage().isDisplayed(), "Onboarding sonrası ana sayfa yüklenemedi!");
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
    void shouldNavigateToLoginPageWhenConfirmLogout() {
        pages.profilePage().clickProfileLogOutButton();
        
        assertTrue(pages.profilePage().getLogOutConfirmTitle(), "Logout onay pop-up'ı görüntülenemedi!");
        
        pages.profilePage().clickLogOutConfirmLogOutButton();
        
        assertTrue(pages.secondPage().isWelcomeToAppDisplayed(), "Logout sonrası Login sayfası açılmadı!");
    }
    
    @Test
    void shouldStayOnProfilePageWhenCancelLogout() {
        pages.profilePage().clickProfileLogOutButton();
        
        pages.profilePage().clickLogOutConfirmCancelButton();
        
        assertTrue(pages.profilePage().isDisplayed(), "Logout iptal edilmesine rağmen Profile sayfasından çıkıldı!");
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