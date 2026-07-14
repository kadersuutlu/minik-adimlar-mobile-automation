package tests;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import api.ContentApi;
import data.TestData;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import java.util.stream.Stream;

import base.BaseTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


@Feature("Okuma Listem")
public class ReadingListTest extends BaseTest {

    private String lastRoute;

    @BeforeAll
    @Step("Test ortamı hazırlandı ve kullanıcı giriş yaptı")
    public void loginOnce() {
        resetApp();
        flow.loginAndCleanStart(
                TestData.LOG_USER_WITH_BABY_EMAIL,
                TestData.LOG_USER_WITH_BABY_PASSWORD
        );
        assertTrue(pages.homePage().isDisplayed(), "Ana sayfa yüklenemedi!");
    }

    @BeforeEach
    @Step("Her test öncesi okuma listeleri API ile temizlendi")
    public void cleanListsBeforeTest() {
        try {
            ContentApi.clearReadingList("BABY", TestData.LOG_USER_WITH_BABY_EMAIL, TestData.LOG_USER_WITH_BABY_PASSWORD);
            ContentApi.clearReadingList("PARENT", TestData.LOG_USER_WITH_BABY_EMAIL, TestData.LOG_USER_WITH_BABY_PASSWORD);
        } catch (Exception e) {
            System.out.println("Setup Cleanup Hatası: " + e.getMessage());
        }
    }

    @Step("Uygulama yeniden başlatılarak taze veri çekilmesi sağlandı")
    private void restartAppForFreshData() {
        driver.terminateApp(APP_PACKAGE);
        driver.activateApp(APP_PACKAGE);
    }

    @Step("Okuma listesi sayfasına {0} rotası üzerinden gidildi")
    private void navigateToReadingList(String route) {
        restartAppForFreshData();

        assertTrue(pages.homePage().isDisplayed(), "Yeniden başlatma sonrası ana sayfa yüklenemedi!");

        lastRoute = route;

        if (route.equals("PROFILE")) {
            pages.homePage().clickProfileIcon();
            assertTrue(pages.profilePage().isDisplayed(), "Profil sayfası yüklenemedi!");
            pages.profilePage().clickProfileReadingListButton();
        } else if (route.equals("CONTENTS")) {
            pages.homePage().clickNavigationContents();
            assertTrue(pages.contentsPage().isDisplayed(), "İçerikler sayfası yüklenemedi!");
            pages.contentsPage().clickContentListReadingListIcon();
        }
        assertTrue(pages.readingListPage().isDisplayed(), "Okuma Listesi sayfasına ulaşılamadı!");
    }

    private String convertToAudience(String tab) {
        return tab.equals("baby") ? "BABY" : "PARENT";
    }

    @Step("{0} sekmesine geçiş yapıldı")
    private void selectTab(String tab) {
        if (tab.equals("baby")) {
            pages.readingListPage().selectBabyTab();
            assertTrue(pages.readingListPage().isBabyTabSelected(), "Bebek sekmesi seçilemedi!");
        } else {
            pages.readingListPage().selectMyselfTab();
            assertTrue(pages.readingListPage().isMyselfTabSelected(), "Ebeveyn sekmesi seçilemedi!");
        }
    }

    @ParameterizedTest(name = "Rota: {0} -> Sekme: {1} - Boş liste uyarısı doğrulanmalı")
    @MethodSource("provideRouteAndTabCombinations")
    @DisplayName("Boş Liste Durumu Kontrolü")
    @Severity(SeverityLevel.NORMAL)
    @Story("Boş liste durumu")
    public void shouldShowEmptyState(String route, String tab) {
        navigateToReadingList(route);
        selectTab(tab);

        assertTrue(pages.readingListPage().isReadingListEmpty(), "API temizlemesine rağmen UI üzerinde liste boş görünmüyor!");

        String emptyText = tab.equals("baby")
                ? pages.readingListPage().getEmptyTextBaby()
                : pages.readingListPage().getEmptyTextParent();

        assertFalse(emptyText.isEmpty(), "Boş liste açıklama metni eksik!");
    }

    @ParameterizedTest(name = "Rota: {0} -> Sekme: {1} - Eklenen öğeler görünür olmalı")
    @MethodSource("provideRouteAndTabCombinations")
    @DisplayName("Dolu Listede Öğe Görünürlüğü Testi")
    @Severity(SeverityLevel.NORMAL)
    @Story("Item görünürlüğü")
    public void shouldDisplayItemsIfExist(String route, String tab) {
        String audience = convertToAudience(tab);
        int testContentId = tab.equals("baby") ? 4 : 16;
        ContentApi.addContentToReadingList(audience, testContentId, TestData.LOG_USER_WITH_BABY_EMAIL, TestData.LOG_USER_WITH_BABY_PASSWORD);

        navigateToReadingList(route);
        selectTab(tab);

        assertTrue(pages.readingListPage().hasItems(), "API ile eklenen öğe UI'da listelenmedi!");
        assertTrue(pages.readingListPage().isItemVisible(0), "İlk öğe görünür değil!");
    }

    @ParameterizedTest(name = "Rota: {0} -> Sekme: {1} - Kaldır ikonuna tıklanınca içerik silinmeli")
    @MethodSource("provideRouteAndTabCombinations")
    @DisplayName("Listeden Öğe Silme Fonksiyonu Testi")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Item silme")
    public void shouldRemoveItemWhenClicked(String route, String tab) {
        ContentApi.addContentToReadingList("BABY", 4, TestData.LOG_USER_WITH_BABY_EMAIL, TestData.LOG_USER_WITH_BABY_PASSWORD);
        ContentApi.addContentToReadingList("PARENT", 16, TestData.LOG_USER_WITH_BABY_EMAIL, TestData.LOG_USER_WITH_BABY_PASSWORD);

        navigateToReadingList(route);
        selectTab(tab);

        pages.readingListPage().clickRemoveIconByIndex(0);
        assertTrue(pages.readingListPage().isItemRemoved(0), "Öğe listeden kaldırılamadı!");
    }

    @AfterEach
    @Step("Test tamamlandı")
    public void tearDownEach() {
        lastRoute = null;
    }

    private static Stream<Arguments> provideRouteAndTabCombinations() {
        return Stream.of(
                Arguments.of("PROFILE", "baby"),
                Arguments.of("PROFILE", "myself"),
                Arguments.of("CONTENTS", "baby"),
                Arguments.of("CONTENTS", "myself")
        );
    }
}