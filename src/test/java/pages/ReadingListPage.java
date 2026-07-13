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

    private final By header =
            AppiumBy.accessibilityId("reading_list_title");

    private final By babyTab =
            AppiumBy.accessibilityId("reading_list_tab_for_baby");

    private final By myselfTab =
            AppiumBy.accessibilityId("reading_list_tab_for_myself");

    private final By items =
            AppiumBy.xpath("//*[contains(@content-desc,'reading_list_item_')]");

    private final By emptyBaby =
            AppiumBy.accessibilityId("reading_list_empty_text_baby");

    private final By emptyParent =
            AppiumBy.accessibilityId("reading_list_empty_text_parent");

    private By reading_list_back_button = AppiumBy.accessibilityId("reading_list_back_button");

    public boolean isDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(header)
        ).isDisplayed();
    }

    public boolean hasItems() {
        return getItemCount() > 0;
    }

    public boolean isReadingListEmpty() {
        return !hasItems();
    }

    public void selectBabyTab() {
        wait.until(ExpectedConditions.elementToBeClickable(babyTab)).click();
        waitForScreenReady();
        wait.until(driver -> isBabyTabSelected());
    }

    public void selectMyselfTab() {
        wait.until(ExpectedConditions.elementToBeClickable(myselfTab)).click();
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

    public boolean isBabyTabSelected() {
        return isTabSelected(babyTab);
    }

    public boolean isMyselfTabSelected() {
        return isTabSelected(myselfTab);
    }

    private boolean isTabSelected(By tabLocator) {
        WebElement tab = wait.until(
                ExpectedConditions.visibilityOfElementLocated(tabLocator)
        );
        String selected = tab.getAttribute("selected");
        return "true".equalsIgnoreCase(selected);
    }

    public int getItemCount() {
        return driver.findElements(items).size();
    }

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
        return AppiumBy.accessibilityId(
                "reading_list_item_reading_list_icon_" + index + "_filled"
        );
    }

    public void clickItemByIndex(int index) {
        scrollToItem(index);
        wait.until(ExpectedConditions.elementToBeClickable(
                itemByIndex(index)
        )).click();
    }

    public void clickRemoveIconByIndex(int index) {
        scrollToItem(index);
        wait.until(ExpectedConditions.elementToBeClickable(
                removeIconByIndex(index)
        )).click();
    }

    public void scrollToItem(int index) {
        String target = "reading_list_item_" + index;

        driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true))" +
                                ".scrollIntoView(new UiSelector().description(\"" + target + "\"))"
                )
        );
    }

    public boolean isItemVisible(int index) {
        return !driver.findElements(itemByIndex(index)).isEmpty();
    }

    public boolean isItemRemoved(int index) {
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(itemByIndex(index))
        );
    }

    public boolean isIconFilled(int index) {
        return !driver.findElements(removeIconByIndex(index)).isEmpty();
    }

    public boolean isContentPresent(String text) {
        return driver.findElements(items)
                .stream()
                .anyMatch(e -> e.getText().contains(text));
    }

    public String getEmptyTextBaby() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(emptyBaby)
        ).getText();
    }

    public String getEmptyTextParent() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(emptyParent)
        ).getText();
    }

    @Step("Okuma listesi ekranından içerikler ekranına geri dönüldü")
    public void clickBBackToContentsPage() {
        click(reading_list_back_button);
    }
}