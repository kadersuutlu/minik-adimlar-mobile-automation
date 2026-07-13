package tests;

import api.ContentApi;
import data.TestData;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import base.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Feature("İçerikler")
public class ContentsTest extends BaseTest {

    @BeforeAll
    public void loginAndNavigateToContentsOnce() {
        resetApp();
        flow.loginAndCleanStart(
                TestData.LOG_USER_WITH_BABY_EMAIL,
                TestData.LOG_USER_WITH_BABY_PASSWORD
        );

        assertTrue(pages.homePage().isDisplayed(), "Onboarding sonrası ana sayfa yüklenemedi!");
        pages.homePage().clickNavigationContents();
        assertTrue(pages.contentsPage().isDisplayed(), "İçerikler sayfası açılamadı!");
    }

    @BeforeEach
    public void backToContents() {
        pages.homePage().clickNavigationContents();
        assertTrue(pages.contentsPage().isDisplayed(), "İçerikler sayfasına geri dönülemedi!");
    }


    private void switchTab(String audience) {
        if (audience.equals("BABY")) {
            pages.contentsPage().clickBabyContentsTab();
        } else if (audience.equals("PARENT")) {
            pages.contentsPage().clickParentContentsTab();
        }
    }

    @ParameterizedTest(name = "Kategori: {0} - İçerik listesi API verilerini içermeli")
    @DisplayName("İçerik Listesi API Doğrulama Testi")
    @ValueSource(strings = {"BABY", "PARENT"})
    @Severity(SeverityLevel.NORMAL)
    @Story("İçerik doğrulama")
    public void contentsShouldMatchApiByAudience(String audience) {
        switchTab(audience);

        String uiTitle = pages.contentsPage().getFirstContentTitle();

        List<String> apiTitles = ContentApi.getContentTitlesByAudience(
                audience,
                TestData.LOG_USER_WITH_BABY_EMAIL,
                TestData.LOG_USER_WITH_BABY_PASSWORD
        );

        assertTrue(apiTitles.contains(uiTitle),
                audience + " kategorisindeki '" + uiTitle + "' içeriği API listesinde bulunamadı!");
    }

    @ParameterizedTest(name = "Kategori: {0} - Arama kutusuna yazılan kelimeye göre içerikler filtrelenmeli")
    @DisplayName("İçerik Arama ve Filtreleme Testi")
    @ValueSource(strings = {"BABY", "PARENT"})
    @Severity(SeverityLevel.NORMAL)
    @Story("Arama")
    public void searchShouldFilterContents(String audience) {
        switchTab(audience);

        pages.contentsPage().enterSearchInput("uyku");
        driver.hideKeyboard();
        String firstTitle = pages.contentsPage().getFirstContentTitle();

        assertTrue(firstTitle.toLowerCase().contains("uyku"),
                audience + " sekmesinde arama içeriği doğru filtreleyemedi!");
    }

    @ParameterizedTest(name = "Kategori: {0} - Seçilen içerik okuma listesine eklenebilmeli")
    @DisplayName("Okuma Listesine İçerik Ekleme Testi")
    @ValueSource(strings = {"BABY", "PARENT"})
    @Severity(SeverityLevel.NORMAL)
    @Story("Okuma listesi")
    public void shouldAddContentToReadingList(String audience) {
        switchTab(audience);

        String uiTitle = pages.contentsPage().getFirstContentTitle();

        int contentId = ContentApi.getContentIdByTitle(
                audience,
                uiTitle,
                TestData.LOG_USER_WITH_BABY_EMAIL,
                TestData.LOG_USER_WITH_BABY_PASSWORD
        );

        pages.contentsPage().clickContentListAddReadingListIcon(0);

        List<Integer> readingListIds = ContentApi.getReadingListContentIds(
                audience,
                TestData.LOG_USER_WITH_BABY_EMAIL,
                TestData.LOG_USER_WITH_BABY_PASSWORD
        );

        assertTrue(readingListIds.contains(contentId),
                audience + " sekmesindeki içerik ID'si backend okuma listesinde bulunamadı!");
    }

    @Test
    @DisplayName("Bildirim ikonuna basılınca Bildirimler Sayfası açılmalı")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigasyon")
    public void shouldOpenNotificationPageWhenClickNotificationIcon() {
        pages.contentsPage().clickContentListNotificationIcon();
        assertTrue(pages.notificationPage().isDisplayed(), "Bildirimler sayfasına yönlenemedi!");

        driver.navigate().back();
    }

    @Test
    @DisplayName("Okuma listesi ikonuna basılınca Okuma Listem Sayfası açılmalı")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigasyon")
    public void shouldOpenReadingListPageWhenClickReadingListIcon() {
        pages.contentsPage().clickContentListReadingListIcon();
        assertTrue(pages.readingListPage().isDisplayed(), "Okuma listesi sayfasına yönlenemedi!");

        pages.readingListPage().clickBBackToContentsPage();
    }

    @Test
    @DisplayName("Sekme değiştirildiğinde içerik listesi güncellenmeli")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sekme navigasyonu")
    public void switchingTabsShouldChangeContentList() {
        pages.contentsPage().clickBabyContentsTab();
        String babyTitle = pages.contentsPage().getFirstContentTitle();

        pages.contentsPage().clickParentContentsTab();
        String parentTitle = pages.contentsPage().getFirstContentTitle();

        assertNotEquals(babyTitle, parentTitle, "Sekme değiştirilmesine rağmen içerik listesi değişmedi!");
    }

    @AfterEach
    public void cleanUpData() {
        try {
            ContentApi.clearReadingList("BABY", TestData.LOG_USER_WITH_BABY_EMAIL, TestData.LOG_USER_WITH_BABY_PASSWORD);
            ContentApi.clearReadingList("PARENT", TestData.LOG_USER_WITH_BABY_EMAIL, TestData.LOG_USER_WITH_BABY_PASSWORD);
        } catch (Exception e) {
            System.out.println("Cleanup: Okuma listesi temizlenirken hata oluştu: " + e.getMessage());
        }
    }
}