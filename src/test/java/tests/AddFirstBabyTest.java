package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import base.AppFlowManager;
import base.BaseTest;
import data.TestData;

public class AddFirstBabyTest extends BaseTest {

    @BeforeEach
    public void setUpPage() {
    	AppFlowManager flow = new AppFlowManager(driver);
        flow.goToLogin();
        pages.loginPage().fillLoginForm(TestData.LOG_VALID_EMAIL, TestData.FP_NEW_PASSWORD);
        driver.hideKeyboard();
        pages.loginPage().clickLogin();
    }

    @Test
    public void submitShouldBeEnabledWhenAllRequiredFieldsFilled() {

        pages.addFirstBabyPage().enterBabyName(TestData.BABY_VALID_NAME);
        driver.hideKeyboard();
        pages.addFirstBabyPage().enterBirthDate(TestData.BABY_VALID_BIRTHDATE);
        driver.hideKeyboard();
        pages.addFirstBabyPage().selectGirl(); // veya TestData.BABY_VALID_GENDER_GIRL
        pages.addFirstBabyPage().selectRelationship(TestData.BABY_VALID_RELATIONSHIP_MOTHER);

        Assert.assertTrue(pages.addFirstBabyPage().isSubmitButtonEnabled(),
                "Submit button should be enabled");
    }

    @Test
    public void submitShouldBeDisabledWhenNameIsEmpty() {

        pages.addFirstBabyPage().enterBabyName(TestData.BABY_EMPTY_NAME);
        driver.hideKeyboard();
        pages.addFirstBabyPage().enterBirthDate(TestData.BABY_VALID_BIRTHDATE);
        driver.hideKeyboard();
        pages.addFirstBabyPage().selectGirl();
        pages.addFirstBabyPage().selectRelationship(TestData.BABY_VALID_RELATIONSHIP_MOTHER);

        Assert.assertFalse(pages.addFirstBabyPage().isSubmitButtonEnabled(),
                "Submit button should be disabled when name is empty");
    }

    @Test
    public void submitShouldBeDisabledWhenBirthDateIsEmpty() {

    	pages.addFirstBabyPage().enterBabyName(TestData.BABY_VALID_NAME);
        driver.hideKeyboard();
        pages.addFirstBabyPage().enterBirthDate(TestData.BABY_EMPTY_BIRTHDATE);
        driver.hideKeyboard();
        pages.addFirstBabyPage().selectGirl();
        pages.addFirstBabyPage().selectRelationship(TestData.BABY_VALID_RELATIONSHIP_MOTHER);

        Assert.assertFalse(pages.addFirstBabyPage().isSubmitButtonEnabled(),
                "Submit button should be disabled when birthdate is empty");
    }

    @Test
    public void submitShouldBeDisabledWhenGenderNotSelected() {

        pages.addFirstBabyPage().enterBabyName(TestData.BABY_VALID_NAME);
        driver.hideKeyboard();
        pages.addFirstBabyPage().enterBirthDate(TestData.BABY_VALID_BIRTHDATE);
        driver.hideKeyboard();
        pages.addFirstBabyPage().selectRelationship(TestData.BABY_VALID_RELATIONSHIP_MOTHER);
        // Cinsiyet seçilmiyor

        Assert.assertFalse(pages.addFirstBabyPage().isSubmitButtonEnabled(),
                "Submit button should be disabled when gender not selected");
    }

    @Test
    public void submitShouldBeDisabledWhenNothingFilled() {

        Assert.assertFalse(pages.addFirstBabyPage().isSubmitButtonEnabled(),
                "Submit button should be disabled when form is empty");
    }
}