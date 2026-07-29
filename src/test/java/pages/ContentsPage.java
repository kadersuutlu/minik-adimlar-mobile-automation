package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class ContentsPage extends BasePage{

	public ContentsPage(AndroidDriver driver) {
		super(driver);
		System.out.println("İçerikler initialized");
	}

    private By contentsHeader = AppiumBy.accessibilityId("content_list_title");

    private By contentListTabForBaby = AppiumBy.accessibilityId("content_list_tab_for_baby");
    private By contentListTabForParent = AppiumBy.accessibilityId("content_list_tab_for_parent");

    private By contentListReadingListIcon = AppiumBy.accessibilityId("content_list_reading_list_icon");
    private By contetListNotificationIcon = AppiumBy.accessibilityId("content_list_notification_icon");

    private By contentListSearchInput = AppiumBy.accessibilityId("content_list_search_input");
    private By contentListAddReadingListIcon = AppiumBy.accessibilityId("content_item_reading_list_icon_0");

    private By contentTitles = AppiumBy
            .xpath("//android.view.ViewGroup[contains(@content-desc,'content_item_')]//android.widget.TextView[1]");

    @Step("İçerikler sayfasının görüntülendiği doğrulanıyor")
    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(contentsHeader)).isDisplayed();
    }

    @Step("İçerikler sayfasının yüklenmesi bekleniyor")
    public void waitForContentsPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(contentsHeader));
    }

    @Step("İlk içerik başlığı alındı")
    public String getFirstContentTitle() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(contentTitles));
            return driver.findElements(contentTitles).get(0).getText();
        } catch (StaleElementReferenceException e) {
            // DOM tam o anda yenilendi, tekrar dene
            wait.until(ExpectedConditions.visibilityOfElementLocated(contentTitles));
            return driver.findElements(contentTitles).get(0).getText();
        }
    }

    @Step("Bebek içerikleri sekmesine tıklandı")
    public void clickBabyContentsTab() {
        click(contentListTabForBaby);
    }

    @Step("Ebeveyn içerikleri sekmesine tıklandı")
    public void clickParentContentsTab() {
        click(contentListTabForParent);
    }

    @Step("Okuma listesi ikonuna tıklandı")
    public void clickContentListReadingListIcon() {
        click(contentListReadingListIcon);
    }

    @Step("Bildirim ikonuna tıklandı")
    public void clickContentListNotificationIcon() {
        click(contetListNotificationIcon);
    }

    @Step("İlk içeriği okuma listesine ekle ikonuna tıklandı")
    public void clickContentListAddReadingListIcon() {
        click(contentListAddReadingListIcon);
    }

    @Step("Arama kutusuna yazıldı: {keyword}")
    public void enterSearchInput(String keyword) {
        clickAndSendKeys(contentListSearchInput, keyword);
    }

    @Step("İçerik okuma listesine eklendi: {index}")
    public void clickContentListAddReadingListIcon(int index) {
        By readingListIcon = AppiumBy.accessibilityId("content_item_reading_list_icon_" + index + "_empty");
        click(readingListIcon);
    }
}