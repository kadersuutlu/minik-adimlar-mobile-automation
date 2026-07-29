package base;

import data.TestData;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.TimeoutException;

public class AppFlowManager {

    private AndroidDriver driver;
    private PageManager pages;

    public AppFlowManager(AndroidDriver driver, PageManager pages) {
        this.driver = driver;
        this.pages = pages;
    }

    private void passWelcomeScreenIfPresent() {
        if (isElementPresent(AppiumBy.accessibilityId("welcome_continue_button"))) {
            pages.firstPage().tapContinue();
        }
    }

    public void goToLogin() {
        passWelcomeScreenIfPresent();
        if (isElementPresent(AppiumBy.accessibilityId("auth_choice_signin_button"))) {
            pages.secondPage().tapLogin();
        }
    }

    public void goToRegister() {
        passWelcomeScreenIfPresent();
        if (isElementPresent(AppiumBy.accessibilityId("auth_choice_signup_button"))) {
            pages.secondPage().tapRegister();
        }
    }

    /**
     * Ana sayfa açıldıktan sonra çıkan 8 adımlık (7 Next + 1 Done) onboarding
     * rehberini dinamik olarak geçer.
     */
    public void passMainOnboarding() {
        int safetyCounter = 0;
        boolean finished = false;

        while (safetyCounter < 15) {
            int status = pages.homePage().getOnboardingStatus();
            System.out.println("Onboarding status: " + status);

            try {
                if (status == 1) {
                    pages.homePage().clickOnboardingNext();
                } else if (status == 2) {
                    pages.homePage().clickOnboardingDone();
                    finished = true;
                    break;
                } else {
                    finished = true;
                    break;
                }
            } catch (TimeoutException e) {
                // Buton, kontrol ile tıklama arasında kaybolmuş olabilir (adım geçişi).
            }

            safetyCounter++;
        }

        if (!finished) {
            throw new IllegalStateException("Onboarding 15 adımda tamamlanamadı!");
        }
    }

    // Tek bir komutla Login olur ve tüm onboarding adımlarını temizler.
    public void loginAndCleanStart(String email, String pass) {
        goToLogin();
        pages.loginPage().fillLoginForm(email, pass);
        driver.hideKeyboard();
        pages.loginPage().clickLogin();

        passMainOnboarding();
    }

    // Yardımcı metot: Element var mı kontrolü (Hızlı kontrol için)
    private boolean isElementPresent(By by) {
        return driver.findElements(by).size() > 0;
    }

    @Step("Silinebilir test kullanıcısı kayıt ediliyor ve ana sayfaya kadar ilerleniyor")
    public String registerDisposableUserForDelete() {
        String email = TestData.generateEmail();
        String phone = TestData.generatePhoneNumber();
        goToLogin();
        pages.loginPage().clickGoToSignUp();
        pages.registerPage().fillRegisterForm("Test",email, phone, TestData.DELETE_CANCEL_TEST_PASSWORD);
        pages.registerPage().clickRegister();

        return email;
    }
}