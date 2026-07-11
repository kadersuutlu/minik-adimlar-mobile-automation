package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import api.ContentApi;
import base.AppFlowManager;
import base.BaseTest;
import data.TestData;

import static org.junit.jupiter.api.Assertions.*;

public class ContentsTest extends BaseTest {


	@BeforeEach
	public void setUpPage() {
		
AppFlowManager flow = new AppFlowManager(driver,pages);
        
        flow.loginAndCleanStart("genctestmuhendis@gmail.com", "Test123");

        assertTrue(pages.homePage().isDisplayed(), "Onboarding sonrası ana sayfa yüklenemedi!");
        pages.homePage().clickNavigationContents();

	}

	@Test
	public void babyContentShouldMatchApi() {

		pages.contentsPage().clickBabyContentsTab();

		String uiTitle = pages.contentsPage().getFirstContentTitle();

		String apiTitle = ContentApi.getFirstContentTitleByAudience("BABY");

		assertEquals(uiTitle, apiTitle);
	}

	@Test
	public void parentContentsShouldMatchApi() {

		pages.contentsPage().clickParentContentsTab();

		String uiTitle = pages.contentsPage().getFirstContentTitle();

		String apiTitle = ContentApi.getFirstContentTitleByAudience("PARENT");

		assertEquals(uiTitle, apiTitle);
	}

	@Test
	public void switchingTabsShouldChangeContentList() {

		pages.contentsPage().clickBabyContentsTab();
	    String babyTitle = pages.contentsPage().getFirstContentTitle();

	    pages.contentsPage().clickParentContentsTab();
	    String parentTitle = pages.contentsPage().getFirstContentTitle();

	    assertNotEquals(babyTitle, parentTitle,
	            "Content list did not change after switching tabs");
	}
	
	@Test
	public void searchShouldFilterContents() {

		pages.contentsPage().enterSearchInput("uyku");

	    String firstTitle = pages.contentsPage().getFirstContentTitle();

	    assertTrue(firstTitle.toLowerCase().contains("uyku"),
	            "Search did not filter content correctly");
	}
	
	@Test
	public void shouldAddContentToReadingList() {

	    String title = pages.contentsPage().getFirstContentTitle();

	    pages.contentsPage().clickContentListAddReadingListIcon();
	    pages.contentsPage().clickContentListReadingListIcon();


	    assertTrue(pages.readingListPage().isContentPresent(title),
	            "Content was not added to reading list");
	}
	
	@Test
	public void shouldOpenNotificationPageWhenClickNotificationIcon() {

		pages.contentsPage().clickContentListNotificationIcon();

		assertTrue(pages.notificationPage().isDisplayed(), "contents page did not redirect to notification page");
	}

	@Test
	public void shouldOpenReadingListPageWhenClickReadingListIcon() {

		pages.contentsPage().clickContentListReadingListIcon();

		assertTrue(pages.readingListPage().isDisplayed(), "Reading page did not redirect to notification page");
	}
}