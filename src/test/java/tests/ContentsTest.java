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
import pages.NotificationPage;
import pages.ReadingListPage;

public class ContentsTest extends BaseTest {

	private ContentsPage contentsPage;
	private LoginPage loginPage;
	private HomePage homePage;
	private NotificationPage notificationPage;
	private ReadingListPage readingListPage;

	@BeforeEach
	public void setUpPage() {

	    homePage = loginAsValidUser();

	    homePage.clickNavigationContents();

	    contentsPage = new ContentsPage(driver);
	    contentsPage.waitForContentsPage();
	}

	@Test
	public void babyContentShouldMatchApi() {

		contentsPage.clickBabyContentsTab();

		String uiTitle = contentsPage.getFirstContentTitle();

		String apiTitle = ContentApi.getFirstContentTitleByAudience("BABY");

		Assert.assertEquals(uiTitle, apiTitle);
	}

	@Test
	public void parentContentsShouldMatchApi() {

		contentsPage.clickParentContentsTab();

		String uiTitle = contentsPage.getFirstContentTitle();

		String apiTitle = ContentApi.getFirstContentTitleByAudience("PARENT");

		Assert.assertEquals(uiTitle, apiTitle);
	}

	@Test
	public void shouldOpenNotificationPageWhenClickNotificationIcon() {

		contentsPage.clickContentListNotificationIcon();

		notificationPage = new NotificationPage(driver);

		Assert.assertTrue(notificationPage.isDisplayed(), "contents page did not redirect to notification page");
	}

	@Test
	public void shouldOpenReadingListPageWhenClickReadingListIcon() {

		contentsPage.clickContentListReadingListIcon();

		readingListPage = new ReadingListPage(driver);

		Assert.assertTrue(readingListPage.isDisplayed(), "Reading page did not redirect to notification page");
	}
}