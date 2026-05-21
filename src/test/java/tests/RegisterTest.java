package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import base.AppFlowManager;
import base.BaseTest;
import data.TestData;

public class RegisterTest extends BaseTest {

	@BeforeEach
	public void setupPage() {
		AppFlowManager flow = new AppFlowManager(driver);
	    flow.goToRegister();
	}

	@Test
    public void successfulRegisterRedirectsToLogin() {

        // Dynamic email oluşturduk
        String email = "test" + System.currentTimeMillis() + "@gmail.com";

        pages.registerPage().enterName(TestData.REG_VALID_NAME);
        pages.registerPage().enterEmail(email);
        pages.registerPage().enterPhone(TestData.REG_VALID_PHONE);
        driver.hideKeyboard();
        pages.registerPage().enterPassword(TestData.REG_VALID_PASSWORD);
        driver.hideKeyboard();
        pages.registerPage().acceptAgreements();
        pages.registerPage().clickRegister();

        Assert.assertTrue(pages.loginPage().isDisplayed(),
                "User should be redirected to Login page after successful registration");
    }

    @Test
    public void registerWithInvalidEmailShowsError() {

        pages.registerPage().enterName(TestData.REG_VALID_NAME);
        driver.hideKeyboard();
        pages.registerPage().enterEmail(TestData.REG_INVALID_EMAIL);
        driver.hideKeyboard();
        pages.registerPage().enterPhone(TestData.REG_VALID_PHONE);
        driver.hideKeyboard();

        String errorText = pages.registerPage().getEmailErrorText();

        Assert.assertTrue(errorText.contains("geçerli"), "Invalid email error message should be displayed");
    }
	
	//1) Şifre zorunludur Beklenen hata: "Şifre zorunludur." Test input: "" // boş string ???????

    @Test
    public void registerWithEmptyPasswordShowsError() {

    	pages.registerPage().enterPassword(TestData.REG_EMPTY_PASSWORD);
        pages.registerPage().enterName(TestData.REG_VALID_NAME);
        driver.hideKeyboard();

        String errorText = pages.registerPage().getPasswordEmptyErrorText();
        Assert.assertTrue(errorText.contains("zorunlu"), "Password required error should be displayed");
    }
	
	//2) Minimum uzunluk (en az 6 karakter) Beklenen hata: "Şifre en az 6 karakter olmalıdır." Test input: "Ab123" // 5 karakter

    @Test
    public void registerWithShortPasswordShowsMinLengthError() {

    	pages.registerPage().enterPassword(TestData.REG_SHORT_PASSWORD);
        pages.registerPage().enterName(TestData.REG_VALID_NAME);
        driver.hideKeyboard();

        String errorText = pages.registerPage().getPasswordMinLengthErrorText();
        Assert.assertTrue(errorText.contains("en az"), "Min length password error should be displayed");
    }

	//3) En az 1 büyük harf içermelidir Beklenen hata: "Şifre en az 1 büyük harf içermelidir." Test input: "test123" // büyük harf yok
	
    @Test
    public void registerWithPasswordWithoutUppercaseShowsError() {

    	pages.registerPage().enterPassword(TestData.REG_NO_UPPERCASE_PASSWORD);
        pages.registerPage().enterName(TestData.REG_VALID_NAME);
        driver.hideKeyboard();
        
        String errorText = pages.registerPage().getPasswordUppercaseErrorText();
        Assert.assertTrue(errorText.contains("büyük harf"), "Uppercase letter error should be displayed");
    }
	
	//4) En az 1 küçük harf içermelidir Beklenen hata: "Şifre en az 1 küçük harf içermelidir." Test input: "TEST123" // küçük harf yok
	
    @Test
    public void registerWithPasswordWithoutLowercaseShowsError() {

    	pages.registerPage().enterPassword(TestData.REG_NO_LOWERCASE_PASSWORD);
        pages.registerPage().enterName(TestData.REG_VALID_NAME);
        driver.hideKeyboard();


        String errorText = pages.registerPage().getPasswordLowerCaseErrorText();
        Assert.assertTrue(errorText.contains("küçük harf"), "Lowercase letter error should be displayed");
    }
}