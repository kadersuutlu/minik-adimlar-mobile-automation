package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AddFirstBabyPage {
	private AndroidDriver driver;
	private WebDriverWait wait;

	public AddFirstBabyPage(AndroidDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		System.out.println("FirstAddBabyPage initialized");
	}

	By addBabyTitle = AppiumBy.accessibilityId("add_baby_title");

	private By babyPhoto = AppiumBy.accessibilityId("add_baby_photo");
	private By babyPhotoAddIcon = AppiumBy.accessibilityId("add_baby_photo_add_icon");
	private By babyPhotoTakePhoto = AppiumBy.accessibilityId("add_baby_photo_take_photo_text");
	private By babyPhotoChooseGallery = AppiumBy.accessibilityId("add_baby_photo_choose_gallery_text");

	private By babyNameInput = AppiumBy.accessibilityId("add_baby_name_input");
	private By babyBirthdateInput = AppiumBy.accessibilityId("add_baby_birthdate_input");

	private By babyGenderGirl = AppiumBy.accessibilityId("add_baby_gender_girl");
	private By BabyGenderBoy = AppiumBy.accessibilityId("add_baby_gender_boy");
	
	private By babyRelationShipDropdown = AppiumBy.accessibilityId("add_baby_relationship_dropdown");
	private By babyMother = AppiumBy.accessibilityId("Anne");
	private By babyFather = AppiumBy.accessibilityId("Baba");
	private By babyCareGiver = AppiumBy.accessibilityId("Bakıcı");
	private By babyOther = AppiumBy.accessibilityId("Diğer");

	private By babySubmitButton = AppiumBy.accessibilityId("add_baby_submit_button");

	public boolean isDisplayed() {
		return driver.findElements(addBabyTitle).size() > 0;
	}

	public void enterBabyName(String name) {
		driver.findElement(babyNameInput).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(babyNameInput)).sendKeys(name);
	}

	public void enterBirthDate(String date) {
		driver.findElement(babyBirthdateInput).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(babyBirthdateInput)).sendKeys(date);
	}

	public void selectGirl() {
		wait.until(ExpectedConditions.elementToBeClickable(babyGenderGirl)).click();
	}

	public void selectBoy() {
		wait.until(ExpectedConditions.elementToBeClickable(BabyGenderBoy)).click();
	}
	
	public void selectRelationship(String type) {
	    wait.until(ExpectedConditions.elementToBeClickable(babyRelationShipDropdown)).click();

	    switch (type.toLowerCase()) {
	        case "anne":
	            wait.until(ExpectedConditions.elementToBeClickable(babyMother)).click();
	            break;
	        case "baba":
	            wait.until(ExpectedConditions.elementToBeClickable(babyFather)).click();
	            break;
	        case "bakıcı":
	            wait.until(ExpectedConditions.elementToBeClickable(babyCareGiver)).click();
	            break;
	        case "diğer":
	            wait.until(ExpectedConditions.elementToBeClickable(babyOther)).click();
	            break;
	    }
	}


	public void clickSubmit() {
		wait.until(ExpectedConditions.elementToBeClickable(babySubmitButton)).click();
	}

	public boolean isSubmitButtonEnabled() {
		return driver.findElement(babySubmitButton).isEnabled();
	}

	public void fillBabyForm(String name, String birthDate, String gender) {
		enterBabyName(name);
		enterBirthDate(birthDate);

		if (gender.equalsIgnoreCase("girl")) {
			selectGirl();
		} else {
			selectBoy();
		}
	}
}