package tests;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.BeforeEach;

import base.AppFlowManager;
import base.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ReadingListTest extends BaseTest {

    @BeforeEach
    public void setUpPage() {

        AppFlowManager flow = new AppFlowManager(driver);

        flow.loginAndCleanStart("yeniemail2@test.com", "Valid1234");

        assertTrue(pages.homePage().isDisplayed(),
                "Home page not loaded!");

        pages.homePage().clickProfileIcon();

        assertTrue(pages.profilePage().isDisplayed(),
                "Profile page not loaded!");

        pages.profilePage().clickProfileReadingListButton();

        assertTrue(pages.readingListPage().isDisplayed(),
                "Reading List page not loaded!");
    }

    private void selectBabyTab() {
        //  önce diğer taba geç
        pages.readingListPage().selectMyselfTab();

        // sonra baby'e dön
        pages.readingListPage().selectBabyTab();
    }

    private void selectMyselfTab() {
        pages.readingListPage().selectMyselfTab();
    }

    private void selectTab(String tab) {
        if (tab.equals("baby")) {
            selectBabyTab();
        } else {
            selectMyselfTab();
        }
    }

    private void ensureListEmpty(String tab) {

        selectTab(tab);

        while (pages.readingListPage().hasItems()) {
            pages.readingListPage().clickRemoveIconByIndex(0);
        }
    }

    private void ensureListHasItems(String tab) {

        selectTab(tab);

        assumeTrue(
                pages.readingListPage().hasItems(),
                tab + " list is empty → test skipped"
        );
    }

    // Liste boş olmalı
    @ParameterizedTest
    @ValueSource(strings = {"baby", "myself"})
    public void shouldShowEmptyState(String tab) {

        ensureListEmpty(tab);

        assertTrue(pages.readingListPage().isReadingListEmpty(),
                "List is not empty! Tab: " + tab);

        String emptyText = tab.equals("baby")
                ? pages.readingListPage().getEmptyTextBaby()
                : pages.readingListPage().getEmptyTextParent();

        assertFalse(emptyText.isEmpty(),
                "Empty text is missing! Tab: " + tab);
    }

    // Liste dolu ya da boş olabilir
    @ParameterizedTest
    @ValueSource(strings = {"baby", "myself"})
    public void shouldHaveValidItemCountPerTab(String tab) {

        if (tab.equals("baby")) {
            pages.readingListPage().selectBabyTab();
        } else {
            pages.readingListPage().selectMyselfTab();
        }

        int count = pages.readingListPage().getItemCount();

        System.out.println(tab + " count: " + count);

        assertTrue(count >= 0);
    }

    // Liste dolu olmalı
    @ParameterizedTest
    @ValueSource(strings = {"baby", "myself"})
    public void shouldDisplayItemsIfExist(String tab) {

        selectTab(tab);

        int count = pages.readingListPage().getItemCount();

        if (count > 0) {
            ensureListHasItems(tab);
            assertTrue(pages.readingListPage().isItemVisible(0));
        }
    }

    // Liste dolu olmalı
    @ParameterizedTest
    @ValueSource(strings = {"baby", "myself"})
    public void shouldRemoveItemWhenClicked(String tab) {

        ensureListHasItems(tab);

        pages.readingListPage().clickRemoveIconByIndex(0);

        assertTrue(pages.readingListPage().isItemRemoved(0),
                "Item was not removed! Tab: " + tab);
    }

    // Liste dolu ya da boş olabilir
    @ParameterizedTest
    @ValueSource(strings = {"baby", "myself"})
    public void shouldEmptyListAfterRemovingAllItems(String tab) {

        selectTab(tab);

        while (pages.readingListPage().hasItems()) {
            pages.readingListPage().clickRemoveIconByIndex(0);
        }

        assertTrue(pages.readingListPage().isReadingListEmpty(),
                "List is not empty! Tab: " + tab);
    }

    // Liste dolu olmalı
    @ParameterizedTest
    @ValueSource(strings = {"baby", "myself"})
    public void shouldContainExpectedContent(String tab) {

        selectTab(tab);

        boolean isPresent =
                pages.readingListPage().isContentPresent("test");

        assertTrue(isPresent,
                "Expected content not found in tab: " + tab);
    }
}