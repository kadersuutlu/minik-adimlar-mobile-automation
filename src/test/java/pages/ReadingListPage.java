package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class ReadingListPage extends BasePage {

    public ReadingListPage(AndroidDriver driver) {
        super(driver);
        System.out.println("ReadingListPage initialized");
    }

    private final By header = AppiumBy.accessibilityId("reading_list_title");

    private final By babyTab = AppiumBy.accessibilityId("reading_list_tab_for_baby");

    private final By myselfTab = AppiumBy.accessibilityId("reading_list_tab_for_myself");

    private final By items = AppiumBy.xpath("//*[contains(@content-desc,'reading_list_item_') and not(contains(@content-desc,'_icon_'))]");

    private final By emptyBaby = AppiumBy.accessibilityId("reading_list_empty_text_baby");

    private final By emptyParent = AppiumBy.accessibilityId("reading_list_empty_text_parent");

    private final By reading_list_back_button = AppiumBy.accessibilityId("reading_list_back_button");

    @Step("Okuma listesi sayfasının görüntülendiği doğrulanıyor")
    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(header)).isDisplayed();
    }

    @Step("Listede öğe var mı kontrol ediliyor")
    public boolean hasItems() {
        return getItemCount() > 0;
    }

    @Step("Liste boş mu kontrol ediliyor")
    public boolean isReadingListEmpty() {
        return !hasItems();
    }

    @Step("Bebek sekmesi seçildi")
    public void selectBabyTab() {
        click(babyTab);
        waitForScreenReady();
        wait.until(driver -> isBabyTabSelected());
    }

    @Step("Ebeveyn sekmesi seçildi")
    public void selectMyselfTab() {
        click(myselfTab);
        waitForScreenReady();
        wait.until(driver -> isMyselfTabSelected());
    }

    private void waitForScreenReady() {
        wait.until(driver ->
                driver.findElements(items).size() > 0
                        || driver.findElements(emptyBaby).size() > 0
                        || driver.findElements(emptyParent).size() > 0
        );
    }

    @Step("Bebek sekmesinin seçili olduğu kontrol ediliyor")
    public boolean isBabyTabSelected() {
        return isTabSelected(babyTab);
    }

    @Step("Ebeveyn sekmesinin seçili olduğu kontrol ediliyor")
    public boolean isMyselfTabSelected() {
        return isTabSelected(myselfTab);
    }

    private boolean isTabSelected(By tabLocator) {
        WebElement tab = wait.until(ExpectedConditions.visibilityOfElementLocated(tabLocator));
        String selected = tab.getAttribute("selected");
        return "true".equalsIgnoreCase(selected);
    }

    @Step("Listedeki öğe sayısı alındı")
    public int getItemCount() {
        return driver.findElements(items).size();
    }

    @Step("Sekmenin boş olduğu kontrol ediliyor: {tab}")
    public boolean isEmpty(String tab) {
        if (tab.equals("baby")) {
            return !driver.findElements(emptyBaby).isEmpty();
        }
        return !driver.findElements(emptyParent).isEmpty();
    }

    private By itemByIndex(int index) {
        return AppiumBy.accessibilityId("reading_list_item_" + index);
    }

    private By removeIconByIndex(int index) {
        return AppiumBy.accessibilityId("reading_list_item_reading_list_icon_" + index + "_filled");
    }

    @Step("Kaldır ikonuna tıklandı: {index}")
    public void clickRemoveIconByIndex(int index) {
        scrollToItem(index);
        click(removeIconByIndex(index));
    }

    @Step("Öğeye kadar kaydırıldı: {index}")
    public void scrollToItem(int index) {
        String target = "reading_list_item_" + index;

        driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true))" +
                                ".scrollIntoView(new UiSelector().description(\"" + target + "\"))"
                )
        );
    }

    @Step("Öğenin görünür olduğu kontrol ediliyor: {index}")
    public boolean isItemVisible(int index) {
        return !driver.findElements(itemByIndex(index)).isEmpty();
    }

    @Step("Öğenin kaldırıldığı doğrulanıyor: {index}")
    public boolean isItemRemoved(int index) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(itemByIndex(index)));
    }

    @Step("Bebek sekmesi boş liste metni alındı")
    public String getEmptyTextBaby() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emptyBaby)).getText();
    }

    @Step("Ebeveyn sekmesi boş liste metni alındı")
    public String getEmptyTextParent() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emptyParent)).getText();
    }

    @Step("Okuma listesi ekranından içerikler ekranına geri dönüldü")
    public void clickBBackToContentsPage() {
        click(reading_list_back_button);
    }
}