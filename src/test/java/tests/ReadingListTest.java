package tests;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import base.AppFlowManager;
import base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Epic("Okuma Listesi")
@Feature("Reading List Yönetimi")
public class ReadingListTest extends BaseTest {

    @BeforeAll
    public void loginOnce() {
        AppFlowManager flow = new AppFlowManager(driver,pages);
        flow.loginAndCleanStart("Kadersutlu34@gmail.com", "Test123");

        assertTrue(pages.homePage().isDisplayed(), "Home page not loaded!");
    }

    @BeforeEach
    public void navigateToReadingList() {
        pages.homePage().clickProfileIcon();
        assertTrue(pages.profilePage().isDisplayed(), "Profile page not loaded!");

        pages.profilePage().clickProfileReadingListButton();
        assertTrue(pages.readingListPage().isDisplayed(), "Reading List page not loaded!");
    }

    private void selectBabyTab() {
        pages.readingListPage().selectMyselfTab();
        pages.readingListPage().selectBabyTab();

        assertTrue(pages.readingListPage().isBabyTabSelected(),
                "Baby tab is not selected!");
    }

    private void selectMyselfTab() {
        pages.readingListPage().selectMyselfTab();

        assertTrue(pages.readingListPage().isMyselfTabSelected(),
                "Myself tab is not selected!");
    }

    private void selectTab(String tab) {
        if (tab.equals("baby")) {
            selectBabyTab();
        } else {
            selectMyselfTab();
        }
    }

    private void clearAllItems() {
        int safety = 0;
        while (pages.readingListPage().hasItems() && safety < 50) {
            pages.readingListPage().clickRemoveIconByIndex(0);
            safety++;
        }
        assertTrue(pages.readingListPage().isReadingListEmpty(),
                "Liste 50 denemede boşaltılamadı, silme işlemi çalışmıyor olabilir.");
    }

    private void ensureListEmpty(String tab) {
        selectTab(tab);
        clearAllItems();
    }

    private void ensureListHasItems(String tab) {
        selectTab(tab);
        assumeTrue(
                pages.readingListPage().hasItems(),
                tab + " list is empty → test skipped"
        );
    }

    @ParameterizedTest(name = "Tab: {0}")
    @ValueSource(strings = {"baby", "myself"})
    @DisplayName("Liste boşken empty state doğru gösterilmeli")
    @Severity(SeverityLevel.NORMAL)
    @Story("Boş liste durumu")
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

    @ParameterizedTest(name = "Tab: {0}")
    @ValueSource(strings = {"baby", "myself"})
    @DisplayName("Liste doluysa item'lar görünür olmalı")
    @Severity(SeverityLevel.NORMAL)
    @Story("Item görünürlüğü")
    public void shouldDisplayItemsIfExist(String tab) {

        selectTab(tab);

        int count = pages.readingListPage().getItemCount();

        if (count > 0) {
            ensureListHasItems(tab);
            assertTrue(pages.readingListPage().isItemVisible(0));
        }
    }

    @ParameterizedTest(name = "Tab: {0}")
    @ValueSource(strings = {"baby", "myself"})
    @DisplayName("Item silme ikonuna tıklanınca item listeden kaldırılmalı")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Item silme")
    public void shouldRemoveItemWhenClicked(String tab) {

        ensureListHasItems(tab);

        pages.readingListPage().clickRemoveIconByIndex(0);

        assertTrue(pages.readingListPage().isItemRemoved(0),
                "Item was not removed! Tab: " + tab);
    }

    @ParameterizedTest(name = "Tab: {0}")
    @ValueSource(strings = {"baby", "myself"})
    @DisplayName("Tüm item'lar silindiğinde liste boş duruma geçmeli")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Item silme")
    public void shouldEmptyListAfterRemovingAllItems(String tab) {

        selectTab(tab);
        clearAllItems();

        assertTrue(pages.readingListPage().isReadingListEmpty(),
                "List is not empty! Tab: " + tab);
    }

    @ParameterizedTest(name = "Tab: {0}")
    @ValueSource(strings = {"baby", "myself"})
    @DisplayName("Beklenen içerik listede yer almalı")
    @Severity(SeverityLevel.MINOR)
    @Story("İçerik doğrulama")
    public void shouldContainExpectedContent(String tab) {

        selectTab(tab);

        boolean isPresent =
                pages.readingListPage().isContentPresent("test");

        assertTrue(isPresent,
                "Expected content not found in tab: " + tab);
    }
}