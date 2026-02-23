package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import api.ContentApi;
import base.AppFlowManager;
import base.BaseTest;
import data.TestData;

public class ContentsTest extends BaseTest {


	@BeforeEach
	public void setUpPage() {
		
		AppFlowManager flow = new AppFlowManager(driver);
        flow.goToLogin();
        pages.loginPage().fillLoginForm(TestData.LOG_VALID_EMAIL, TestData.FP_NEW_PASSWORD);
        driver.hideKeyboard();
        pages.loginPage().clickLogin();

	    pages.homePage().clickNavigationContents();

	}

	@Test
	public void babyContentShouldMatchApi() {

		pages.contentsPage().clickBabyContentsTab();

		String uiTitle = pages.contentsPage().getFirstContentTitle();

		String apiTitle = ContentApi.getFirstContentTitleByAudience("BABY");

		Assert.assertEquals(uiTitle, apiTitle);
	}

	@Test
	public void parentContentsShouldMatchApi() {

		pages.contentsPage().clickParentContentsTab();

		String uiTitle = pages.contentsPage().getFirstContentTitle();

		String apiTitle = ContentApi.getFirstContentTitleByAudience("PARENT");

		Assert.assertEquals(uiTitle, apiTitle);
	}

	@Test
	public void switchingTabsShouldChangeContentList() {

		pages.contentsPage().clickBabyContentsTab();
	    String babyTitle = pages.contentsPage().getFirstContentTitle();

	    pages.contentsPage().clickParentContentsTab();
	    String parentTitle = pages.contentsPage().getFirstContentTitle();

	    Assert.assertNotEquals(babyTitle, parentTitle,
	            "Content list did not change after switching tabs");
	}
	
	@Test
	public void searchShouldFilterContents() {

		pages.contentsPage().enterSearchInput("uyku");

	    String firstTitle = pages.contentsPage().getFirstContentTitle();

	    Assert.assertTrue(firstTitle.toLowerCase().contains("uyku"),
	            "Search did not filter content correctly");
	}
	
	@Test
	public void shouldAddContentToReadingList() {

	    String title = pages.contentsPage().getFirstContentTitle();

	    pages.contentsPage().clickContentListAddReadingListIcon();
	    pages.contentsPage().clickContentListReadingListIcon();


	    Assert.assertTrue(pages.readingListPage().isContentPresent(title),
	            "Content was not added to reading list");
	}
	
	@Test
	public void shouldOpenNotificationPageWhenClickNotificationIcon() {

		pages.contentsPage().clickContentListNotificationIcon();

		Assert.assertTrue(pages.notificationPage().isDisplayed(), "contents page did not redirect to notification page");
	}

	@Test
	public void shouldOpenReadingListPageWhenClickReadingListIcon() {

		pages.contentsPage().clickContentListReadingListIcon();

		Assert.assertTrue(pages.readingListPage().isDisplayed(), "Reading page did not redirect to notification page");
	}
}