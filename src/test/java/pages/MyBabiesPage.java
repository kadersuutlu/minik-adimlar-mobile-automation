package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class MyBabiesPage extends BasePage {

	public MyBabiesPage(AndroidDriver driver) {
		super(driver);
		System.out.println("Bebeklerim initialized");
	}

    private final By myBabiesTitle = AppiumBy.accessibilityId("my_babies_title");
    private final By allEditIcons = AppiumBy.xpath("//*[contains(@content-desc, 'my_babies_edit_icon_')]");
    private final By addBabyButton = AppiumBy.accessibilityId("my_babies_add_button");

    private final By babyModalTitle = AppiumBy.accessibilityId("baby_modal_title");
    private final By babyModalNameInput = AppiumBy.accessibilityId("baby_modal_name_input");
    private final By babyModalBirthDateInput = AppiumBy.accessibilityId("baby_modal_birthdate_input");
    private final By babyModalGenderGirl = AppiumBy.accessibilityId("baby_modal_gender_girl");
    private final By babyModalGenderBoy = AppiumBy.accessibilityId("baby_modal_gender_boy");
    private final By babyModalRelationShipDropDown = AppiumBy.accessibilityId("baby_modal_relationship_dropdown");
    private final By babyModalRelationShipMother = AppiumBy.accessibilityId("Anne");
    private final By babyModalRelationShipFather = AppiumBy.accessibilityId("Baba");
    private final By babyModalRelationShipCaregiver = AppiumBy.accessibilityId("Bakıcı");
    private final By babyModalRelationShipOther = AppiumBy.accessibilityId("Diğer");
    private final By babyModalSaveButton = AppiumBy.accessibilityId("baby_modal_save_button");
    private final By babyModalCancelButton = AppiumBy.accessibilityId("baby_modal_cancel_button");

    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(myBabiesTitle)).isDisplayed();
    }

    public void clickAddBabyButton() {
        driver.findElement(addBabyButton).click();
    }

    public void clickEditIconByIndex(int index) {
        List<WebElement> editIcons = driver.findElements(allEditIcons);
        if (index < editIcons.size()) {
            editIcons.get(index).click();
        } else {
            throw new IndexOutOfBoundsException("Listede " + index + " indeksine sahip bir bebek bulunamadı.");
        }
    }

    // Modal Görünürlük Kontrolü
    public boolean isModalDisplayed() {
        try {
            return driver.findElement(babyModalTitle).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    // Modalın o anki Başlık Yazısını Alır (Ekleme mi Güncelleme mi olduğunu anlamak için)
    public String getModalTitleText() {
        return driver.findElement(babyModalTitle).getText();
    }

    // Cinsiyet ve İlişki Seçimleri
    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("Kız")) {
            driver.findElement(babyModalGenderGirl).click();
        } else if (gender.equalsIgnoreCase("Erkek")) {
            driver.findElement(babyModalGenderBoy).click();
        }
    }

    public void selectRelationship(String relationship) {
        driver.findElement(babyModalRelationShipDropDown).click();
        switch (relationship) {
            case "Anne": driver.findElement(babyModalRelationShipMother).click(); break;
            case "Baba": driver.findElement(babyModalRelationShipFather).click(); break;
            case "Bakıcı": driver.findElement(babyModalRelationShipCaregiver).click(); break;
            case "Diğer": driver.findElement(babyModalRelationShipOther).click(); break;
            default: throw new IllegalArgumentException("Geçersiz ilişki türü: " + relationship);
        }
    }

    // Formu dolduran ortak metot
    public void fillBabyForm(String name, String birthDate, String gender, String relationship) {
        WebElement nameField = driver.findElement(babyModalNameInput);
        nameField.clear();
        nameField.sendKeys(name);

        WebElement dateField = driver.findElement(babyModalBirthDateInput);
        dateField.clear();
        dateField.sendKeys(birthDate);

        selectGender(gender);
        selectRelationship(relationship);
    }

    public void clickSaveButton() {
        driver.findElement(babyModalSaveButton).click();
    }

    public void clickCancelButton() {
        driver.findElement(babyModalCancelButton).click();
    }
}