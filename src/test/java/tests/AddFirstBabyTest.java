package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import base.AppFlowManager;
import base.BaseTest;
import data.TestData;
import utils.BabyCleanupUtil;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Feature("İlk Bebeğini Ekle")
public class AddFirstBabyTest extends BaseTest {

    private String addedBabyName;

    @BeforeEach
    public void setUpPage() {
        resetApp();
        flow.goToLogin();
        pages.loginPage().fillLoginForm(
                TestData.LOG_USER_WITHOUT_BABY_EMAIL,
                TestData.LOG_USER_WITHOUT_BABY_PASSWORD
        );
        driver.hideKeyboard();
        pages.loginPage().clickLogin();
    }

    @Test
    @DisplayName("Geçerli bilgilerle bebek eklenince ana sayfaya yönlendirilmeli")
    @Description("Ad, doğum tarihi, cinsiyet ve ilişki türü girilip kaydedilince ana sayfa görüntülenmeli")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Başarılı bebek ekleme")
    public void successfulBabySubmission_addsBabyAndRedirectsToHome() {
        String babyName = TestData.BABY_VALID_NAME;

        pages.addFirstBabyPage().enterBabyName(babyName);
        driver.hideKeyboard();
        pages.addFirstBabyPage().enterBirthDate(TestData.BABY_VALID_BIRTHDATE);
        driver.hideKeyboard();
        pages.addFirstBabyPage().selectGirl();
        pages.addFirstBabyPage().selectRelationship(TestData.BABY_VALID_RELATIONSHIP_MOTHER);
        pages.addFirstBabyPage().clickSubmit();

        flow.passMainOnboarding();

        assertTrue(pages.homePage().isDisplayed(), "Bebek eklendikten sonra ana sayfa görüntülenmeli");

        addedBabyName = babyName;
    }

    @Test
    @DisplayName("Tüm zorunlu alanlar doldurulunca kaydet butonu aktif olmalı")
    @Severity(SeverityLevel.NORMAL)
    @Story("Buton durumu")
    public void submitShouldBeEnabledWhenAllRequiredFieldsFilled() {
        pages.addFirstBabyPage().enterBabyName(TestData.BABY_VALID_NAME);
        driver.hideKeyboard();
        pages.addFirstBabyPage().enterBirthDate(TestData.BABY_VALID_BIRTHDATE);
        driver.hideKeyboard();
        pages.addFirstBabyPage().selectGirl();
        pages.addFirstBabyPage().selectRelationship(TestData.BABY_VALID_RELATIONSHIP_MOTHER);

        assertTrue(pages.addFirstBabyPage().isSubmitButtonEnabled(), "Submit button should be enabled");
    }

    @Test
    @DisplayName("İsim boşken kaydet butonu pasif olmalı")
    @Severity(SeverityLevel.NORMAL)
    @Story("Buton durumu")
    public void submitShouldBeDisabledWhenNameIsEmpty() {
        pages.addFirstBabyPage().enterBabyName(TestData.BABY_EMPTY_NAME);
        driver.hideKeyboard();
        pages.addFirstBabyPage().enterBirthDate(TestData.BABY_VALID_BIRTHDATE);
        driver.hideKeyboard();
        pages.addFirstBabyPage().selectGirl();
        pages.addFirstBabyPage().selectRelationship(TestData.BABY_VALID_RELATIONSHIP_MOTHER);

        assertFalse(pages.addFirstBabyPage().isSubmitButtonEnabled(), "Submit button should be disabled when name is empty");
    }

    @Test
    @DisplayName("Doğum tarihi boşken kaydet butonu pasif olmalı")
    @Severity(SeverityLevel.NORMAL)
    @Story("Buton durumu")
    public void submitShouldBeDisabledWhenBirthDateIsEmpty() {
        pages.addFirstBabyPage().enterBabyName(TestData.BABY_VALID_NAME);
        driver.hideKeyboard();
        pages.addFirstBabyPage().enterBirthDate(TestData.BABY_EMPTY_BIRTHDATE);
        driver.hideKeyboard();
        pages.addFirstBabyPage().selectGirl();
        pages.addFirstBabyPage().selectRelationship(TestData.BABY_VALID_RELATIONSHIP_MOTHER);

        assertFalse(pages.addFirstBabyPage().isSubmitButtonEnabled(), "Submit button should be disabled when birthdate is empty");
    }

    @Test
    @DisplayName("Cinsiyet seçilmezse kaydet butonu pasif olmalı")
    @Severity(SeverityLevel.NORMAL)
    @Story("Buton durumu")
    public void submitShouldBeDisabledWhenGenderNotSelected() {
        pages.addFirstBabyPage().enterBabyName(TestData.BABY_VALID_NAME);
        driver.hideKeyboard();
        pages.addFirstBabyPage().enterBirthDate(TestData.BABY_VALID_BIRTHDATE);
        driver.hideKeyboard();
        pages.addFirstBabyPage().selectRelationship(TestData.BABY_VALID_RELATIONSHIP_MOTHER);

        assertFalse(pages.addFirstBabyPage().isSubmitButtonEnabled(), "Submit button should be disabled when gender not selected");
    }

    @Test
    @DisplayName("Form tamamen boşken kaydet butonu pasif olmalı")
    @Severity(SeverityLevel.MINOR)
    @Story("Buton durumu")
    public void submitShouldBeDisabledWhenNothingFilled() {
        assertFalse(pages.addFirstBabyPage().isSubmitButtonEnabled(), "Submit button should be disabled when form is empty");
    }

    @Test
    @DisplayName("Kamera seçeneğine tıklanınca izin popup'ı gösterilmeli")
    @Severity(SeverityLevel.NORMAL)
    @Story("İzin akışı")
    public void takePhotoOption_showsPermissionDialog() {
        pages.addFirstBabyPage().clickPhotoAddIcon();
        pages.addFirstBabyPage().clickTakePhotoOption();

        assertTrue(pages.addFirstBabyPage().isPermissionDialogDisplayed(),
                "Kamera seçeneğine tıklanınca izin popup'ı gösterilmeli");

        pages.addFirstBabyPage().grantPermission();
    }

    @Test
    @Disabled("Galeri seçici (Photo Picker) locator'ı henüz belirlenmedi, bulunca aktif edilecek")
    @DisplayName("Galeri seçeneğine tıklanınca fotoğraf seçici açılmalı")
    @Severity(SeverityLevel.NORMAL)
    @Story("İzin akışı")
    public void chooseGalleryOption_showsPermissionDialog() {
        pages.addFirstBabyPage().clickPhotoAddIcon();
        pages.addFirstBabyPage().clickChooseGalleryOption();

        assertTrue(pages.addFirstBabyPage().isPermissionDialogDisplayed(),
                "Galeri seçeneğine tıklanınca izin popup'ı gösterilmeli");
    }

    @AfterEach
    public void cleanupBaby() {
        if (addedBabyName != null) {
            BabyCleanupUtil.deleteTestBaby(
                    TestData.LOG_USER_WITHOUT_BABY_EMAIL,
                    TestData.LOG_USER_WITHOUT_BABY_PASSWORD,
                    addedBabyName
            );
            addedBabyName = null;
        }
    }
}