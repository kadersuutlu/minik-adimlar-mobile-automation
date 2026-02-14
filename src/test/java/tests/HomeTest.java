package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import api.ContentApi;
import base.AppFlowManager;
import base.BaseTest;
import pages.ContentsPage;
import pages.HomePage;
import pages.LoginPage;

public class HomeTest extends BaseTest {
	private HomePage homePage;
	private ContentsPage contentsPage;
	private LoginPage loginPage;

	@BeforeEach
	public void setUpPage() {
		AppFlowManager flow = new AppFlowManager(driver);

		flow.goToLogin();

		loginPage = new LoginPage(driver);
		loginPage.enterEmail("validuser@gmail.com");
		loginPage.enterPassword("Valid123");
		driver.hideKeyboard();

		loginPage.clickLogin();

		homePage = new HomePage(driver);
		homePage.waitForHomePage();
		contentsPage = new ContentsPage(driver);
	}

	@Test
	public void babyContentShouldMatchApi() {

		homePage.clickHomeBabyContentSeeAllText();

		String uiTitle = contentsPage.getFirstContentTitle();

		String apiTitle = ContentApi.getFirstContentTitleByAudience("BABY");

		Assert.assertEquals(uiTitle, apiTitle);
	}

	@Test
	public void parentContentsShouldMatchApi() {

		homePage.clickHomeParentContentSeeAllText();

		String uiTitle = contentsPage.getFirstContentTitle();

		String apiTitle = ContentApi.getFirstContentTitleByAudience("PARENT");

		Assert.assertEquals(uiTitle, apiTitle);
	}
}