package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import api.ContentApi;
import base.AppFlowManager;
import base.BaseTest;
import data.TestData;

public class HomeTest extends BaseTest {

	@BeforeEach
	public void setUpPage() {

		AppFlowManager flow = new AppFlowManager(driver);

		flow.loginAndCleanStart("yeniemail2@test.com", "Valid1234");

		assertTrue(pages.homePage().isDisplayed(), "Onboarding sonrası ana sayfa yüklenemedi!");

	}

	@Test
	public void babyContentShouldMatchApi() {

		pages.homePage().clickHomeBabyContentSeeAllText();

		String uiTitle = pages.contentsPage().getFirstContentTitle();

		String apiTitle = ContentApi.getFirstContentTitleByAudience("BABY");

		Assert.assertEquals(uiTitle, apiTitle);
	}

	@Test
	public void parentContentsShouldMatchApi() {

		pages.homePage().clickHomeParentContentSeeAllText();

		String uiTitle = pages.contentsPage().getFirstContentTitle();

		String apiTitle = ContentApi.getFirstContentTitleByAudience("PARENT");

		Assert.assertEquals(uiTitle, apiTitle);
	}

	@Test
	public void myBabyPageOpenWhenClickHowIsYourBabyTodayButton() {

		pages.homePage().clickHomeBabyFeelingCard();

		Assert.assertTrue(pages.myBabyPage().isDisplayed(), "home page did not redirect to my baby page");
	}

	@Test
	public void notificationPageOpenWhenClickNotificationButton() {

		pages.homePage().clickHomeNotificationIcon();

		Assert.assertTrue(pages.notificationPage().isDisplayed(), "home page did not redirect to notification page");
	}

	@Test
	public void addBabyModalShouldBeVisibleWhenClickAddBabyButton() {

		pages.homePage().clickHomeBabyCardAddIcon();

		Assert.assertTrue(pages.addBabyPage().isDisplayed(), "Add Baby modal did not appear");
	}

	@Test
	public void navigateToMyBabiesPage() {

		pages.homePage().clickNavigationMyBaby();

		Assert.assertTrue(pages.myBabyPage().isDisplayed(), "home page did not redirect to my baby page");
	}

	@Test
	public void navigateToContentsPage() {

		pages.homePage().clickNavigationContents();

		Assert.assertTrue(pages.contentsPage().isDisplayed(), "home page did not redirect to contents page");
	}

	@Test
	public void navigateToScedulePage() {

		pages.homePage().clickNavigationSchedule();

		Assert.assertTrue(pages.scedulePage().isDisplayed(), "home page did not redirect to scedule page");
	}
}