package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddFirstBabyPage extends BasePage{

	public AddFirstBabyPage(AndroidDriver driver) {
		super(driver);
		System.out.println("İlk Bebeğini Ekle initialized");
	}

    By addBabyTitle = AppiumBy.accessibilityId("add_baby_title");

    private By photoAddIcon = AppiumBy.accessibilityId("add_baby_photo");
    private By takePhotoOption = By.xpath("//android.view.ViewGroup[@content-desc=\"add_baby_photo_take_photo_text\"]");
    private By chooseGalleryOption = By.xpath("//android.view.ViewGroup[@content-desc=\"add_baby_photo_choose_gallery_text\"]");

    private By permissionAllowButton = By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
    private By permissionDenyButton = By.id("com.android.permissioncontroller:id/permission_deny_button");

    private By babyNameInput = AppiumBy.accessibilityId("add_baby_name_input");
    private By babyBirthdateInput = AppiumBy.accessibilityId("add_baby_birthdate_input");

    private By babyGenderGirl = AppiumBy.accessibilityId("add_baby_gender_girl");
    private By BabyGenderBoy = AppiumBy.accessibilityId("add_baby_gender_boy");

    private By babyRelationShipDropdown = AppiumBy.accessibilityId("add_baby_relationship_dropdown");
    private By babyMother = AppiumBy.accessibilityId("add_baby_relationship_dropdown_option_0");
    private By babyFather = AppiumBy.accessibilityId("add_baby_relationship_dropdown_option_1");
    private By babyCareGiver = AppiumBy.accessibilityId("add_baby_relationship_dropdown_option_2");
    private By babyOther = AppiumBy.accessibilityId("add_baby_relationship_dropdown_option_3");

    private By babySubmitButton = AppiumBy.accessibilityId("add_baby_submit_button");

    @Step("İlk bebeğini ekle sayfasının görüntülendiği doğrulanıyor")
    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(addBabyTitle)).isDisplayed();
    }

    @Step("Bebek fotoğrafı ikonuna tıklandı")
    public void clickPhotoAddIcon() {
        click(photoAddIcon);
    }

    @Step("Kamera ile çek seçeneğine tıklandı")
    public void clickTakePhotoOption() {
        click(takePhotoOption);
    }

    @Step("Galeriden seç seçeneğine tıklandı")
    public void clickChooseGalleryOption() {
        click(chooseGalleryOption);
    }

    @Step("İzin popup'ı görüntüleniyor mu kontrol ediliyor")
    public boolean isPermissionDialogDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            return shortWait.until(ExpectedConditions.visibilityOfElementLocated(permissionAllowButton)) != null;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("İzin verildi")
    public void grantPermission() {
        click(permissionAllowButton);
    }

    @Step("Bebek adı girildi: {name}")
    public void enterBabyName(String name) {
        clickAndSendKeys(babyNameInput, name);
    }

    @Step("Doğum tarihi girildi: {date}")
    public void enterBirthDate(String date) {
        clickAndSendKeys(babyBirthdateInput, date);
    }

    @Step("Kız cinsiyeti seçildi")
    public void selectGirl() {
        click(babyGenderGirl);
    }

    @Step("Erkek cinsiyeti seçildi")
    public void selectBoy() {
        click(BabyGenderBoy);
    }

    @Step("İlişki türü seçildi: {type}")
    public void selectRelationship(String type) {
        click(babyRelationShipDropdown);

        switch (type.toLowerCase()) {
            case "anne":
                click(babyMother);
                break;
            case "baba":
                click(babyFather);
                break;
            case "bakıcı":
                click(babyCareGiver);
                break;
            case "diğer":
                click(babyOther);
                break;
        }
    }

    @Step("Kaydet butonuna tıklandı")
    public void clickSubmit() {
        click(babySubmitButton);
    }

    @Step("Kaydet butonu aktif mi kontrol ediliyor")
    public boolean isSubmitButtonEnabled() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(babySubmitButton)).isEnabled();
    }

    @Step("Bebek formu dolduruldu")
    public void fillBabyForm(String name, String birthDate, String gender) {
        enterBabyName(name);
        driver.hideKeyboard();
        enterBirthDate(birthDate);
        driver.hideKeyboard();

        if (gender.equalsIgnoreCase("girl")) {
            selectGirl();
        } else {
            selectBoy();
        }

        selectRelationship("anne");
    }
}