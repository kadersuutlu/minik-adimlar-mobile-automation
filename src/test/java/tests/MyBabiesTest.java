package tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.testng.AssertJUnit.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import base.AppFlowManager;
import base.BaseTest;
import org.testng.AssertJUnit;

public class MyBabiesTest extends BaseTest {

    @BeforeEach
    public void setUpPage() {
        AppFlowManager flow = new AppFlowManager(driver);
        
        flow.loginAndCleanStart("yeniemail2@test.com", "Valid1234");

        assertTrue(pages.homePage().isDisplayed(), "Onboarding sonrası ana sayfa yüklenemedi!");
        pages.homePage().clickProfileIcon();
        
        assertTrue(pages.profilePage().isDisplayed(), "Profil sayfası açılmadı!");
        pages.profilePage().clickProfileMyBabiesButton();

        assertTrue(pages.myBabiesPage().isDisplayed(), "Bebeklerim sayfası yüklenemedi!");
    }

    @Test
    public void testAddNewBabySuccessfully() {
        // 1. "Bebek Ekle" butonuna tıkla
        pages.myBabiesPage().clickAddBabyButton();

        // 2. Doğrulama: Modal açıldı mı ve başlığı "Bebek Ekle" mi?
        assertTrue(pages.myBabiesPage().isModalDisplayed(), "Modal açılmadı!");
        AssertJUnit.assertEquals("Ekleme modal başlığı hatalı!", "Bebek Ekle", pages.myBabiesPage().getModalTitleText());
        // 3. Formu doldur ve Kaydet
        String babyName = "Alya_" + System.currentTimeMillis() / 1000;
        pages.myBabiesPage().fillBabyForm(babyName, "15.10.2025", "Kız", "Anne");
        pages.myBabiesPage().clickSaveButton();

        // 4. Kapanış Kontrolleri
        assertTrue(pages.myBabiesPage().isDisplayed(), "Bebeklerim sayfasına geri dönülemedi!");
    }

    @Test
    public void testEditFirstBabySuccessfully() {
        // Ön koşul: Listede en az bir bebek olmalı

        // 1. İlk bebeğin düzenle ikonuna tıkla
        pages.myBabiesPage().clickEditIconByIndex(0);

        // 2. Doğrulama: Aynı modal açıldı mı ve başlığı bu sefer "Bebek Güncelle" mi?
        assertTrue(pages.myBabiesPage().isModalDisplayed(), "Modal açılmadı!");
        AssertJUnit.assertEquals("Güncelleme modal başlığı hatalı!", "Bebek Güncelle", pages.myBabiesPage().getModalTitleText());
        // 3. Formu güncelle ve Kaydet
        pages.myBabiesPage().fillBabyForm("Toprak su Yeni", "01.01.2026", "Erkek", "Baba");
        pages.myBabiesPage().clickSaveButton();

        // 4. Kapanış Kontrolleri
        assertTrue(pages.myBabiesPage().isDisplayed(), "Bebeklerim sayfası yüklenemedi!");
    }

    @Test
    public void testCancelModalAction() {
        // 1. Modalı aç ve İptal et
        pages.myBabiesPage().clickAddBabyButton();
        assertTrue(pages.myBabiesPage().isModalDisplayed(), "Modal açılmadı!");

        pages.myBabiesPage().clickCancelButton();

        // 2. Modalın kapandığını ve verinin kaydedilmediğini doğrula
        assertFalse(pages.myBabiesPage().isModalDisplayed(), "Cancel butonuna basılmasına rağmen modal kapanmadı!");
        assertTrue(pages.myBabiesPage().isDisplayed(), "Ana ekran aktif değil!");
    }
}