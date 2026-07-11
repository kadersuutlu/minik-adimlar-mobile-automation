package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import api.ContentApi;
import base.AppFlowManager;
import base.BaseTest;
import data.TestData;

public class HomeTest extends BaseTest {

	@BeforeEach
	public void setUpPage() {

		AppFlowManager flow = new AppFlowManager(driver,pages);

		flow.loginAndCleanStart("genctestmuhendis@gmail.com", "Test123");

		assertTrue(pages.homePage().isDisplayed(), "Onboarding sonrası ana sayfa yüklenemedi!");

	}

	@Test
	public void babyContentShouldMatchApi() {

		pages.homePage().clickHomeBabyContentSeeAllText();

		String uiTitle = pages.contentsPage().getFirstContentTitle();

		String apiTitle = ContentApi.getFirstContentTitleByAudience("BABY");

		assertEquals(uiTitle, apiTitle);
	}

	@Test
	public void parentContentsShouldMatchApi() {

		pages.homePage().clickHomeParentContentSeeAllText();

		String uiTitle = pages.contentsPage().getFirstContentTitle();

		String apiTitle = ContentApi.getFirstContentTitleByAudience("PARENT");

		assertEquals(uiTitle, apiTitle);
	}

	@Test
	public void myBabyPageOpenWhenClickHowIsYourBabyTodayButton() {

		pages.homePage().clickHomeBabyFeelingCard();

		assertTrue(pages.myBabyPage().isDisplayed(), "home page did not redirect to my baby page");
	}

	@Test
	public void notificationPageOpenWhenClickNotificationButton() {

		pages.homePage().clickHomeNotificationIcon();

		assertTrue(pages.notificationPage().isDisplayed(), "home page did not redirect to notification page");
	}

	@Test
	public void addBabyModalShouldBeVisibleWhenClickAddBabyButton() {

		pages.homePage().clickHomeBabyCardAddIcon();

		assertTrue(pages.addBabyPage().isDisplayed(), "Add Baby modal did not appear");
	}

	@Test
	public void navigateToMyBabiesPage() {

		pages.homePage().clickNavigationMyBaby();

		assertTrue(pages.myBabyPage().isDisplayed(), "home page did not redirect to my baby page");
	}

	@Test
	public void navigateToContentsPage() {

		pages.homePage().clickNavigationContents();

		assertTrue(pages.contentsPage().isDisplayed(), "home page did not redirect to contents page");
	}

	@Test
	public void navigateToScedulePage() {

		pages.homePage().clickNavigationSchedule();

		assertTrue(pages.scedulePage().isDisplayed(), "home page did not redirect to scedule page");
	}
}