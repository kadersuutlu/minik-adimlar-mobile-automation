package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import base.AppFlowManager;
import base.BaseTest;
import pages.AddFirstBabyPage;
import pages.LoginPage;

public class AddFirstBabyTest extends BaseTest {
	AddFirstBabyPage addFirstBabyPage;
	LoginPage loginPage;

	@BeforeEach
	public void setUpPage() {

		AppFlowManager flow = new AppFlowManager(driver);

		flow.goToLogin();

		loginPage = new LoginPage(driver);
		loginPage.fillLoginForm("validuser2@gmail.com", "Valid123");
		driver.hideKeyboard();
		loginPage.clickLogin();

		addFirstBabyPage = new AddFirstBabyPage(driver);
		Assert.assertTrue(addFirstBabyPage.isDisplayed(), "Add First Baby page not displayed");
	}

	@Test
	public void submitShouldBeEnabledWhenAllRequiredFieldsFilled() {
		addFirstBabyPage.enterBabyName("Mihra");
		driver.hideKeyboard();
		addFirstBabyPage.enterBirthDate("17.02.2024");
		driver.hideKeyboard();
		addFirstBabyPage.selectGirl();
		addFirstBabyPage.selectRelationship("Anne");

		Assert.assertTrue(addFirstBabyPage.isSubmitButtonEnabled(), "Submit button should be enabled");
	}

	@Test
	public void submitShouldBeDisabledWhenNameIsEmpty() {
		addFirstBabyPage.enterBabyName("");
		driver.hideKeyboard();
		addFirstBabyPage.enterBirthDate("17.02.2024");
		driver.hideKeyboard();
		addFirstBabyPage.selectGirl();
		addFirstBabyPage.selectRelationship("Anne");

		Assert.assertFalse(addFirstBabyPage.isSubmitButtonEnabled(),
				"Submit button should be disabled when name is empty");
	}

	@Test
	public void submitShouldBeDisabledWhenBirthDateIsEmpty() {
		addFirstBabyPage.enterBabyName("Mihra");
		driver.hideKeyboard();
		addFirstBabyPage.enterBirthDate("");
		driver.hideKeyboard();
		addFirstBabyPage.selectGirl();
		addFirstBabyPage.selectRelationship("Anne");

		Assert.assertFalse(addFirstBabyPage.isSubmitButtonEnabled(),
				"Submit button should be disabled when birthdate is empty");
	}

	@Test
	public void submitShouldBeDisabledWhenGenderNotSelected() {
		addFirstBabyPage.enterBabyName("Mihra");
		driver.hideKeyboard();
		addFirstBabyPage.enterBirthDate("17.02.2024");
		driver.hideKeyboard();
		addFirstBabyPage.selectRelationship("Anne");

		Assert.assertFalse(addFirstBabyPage.isSubmitButtonEnabled(),
				"Submit button should be disabled when gender not selected");
	}

	@Test
	public void submitShouldBeDisabledWhenNothingFilled() {
		Assert.assertFalse(addFirstBabyPage.isSubmitButtonEnabled(),
				"Submit button should be disabled when form is empty");
	}
}