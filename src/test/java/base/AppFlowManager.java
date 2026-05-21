package base;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AppFlowManager {

	private AndroidDriver driver;
	private PageManager pages;

	public AppFlowManager(AndroidDriver driver) {
		this.driver = driver;
		this.pages = new PageManager(driver);
	}

	public void goToLogin() {
		if (isElementPresent(AppiumBy.accessibilityId("welcome_continue_button"))) {
			pages.firstPage().tapContinue();
		}

		if (isElementPresent(AppiumBy.accessibilityId("auth_choice_signin_button"))) {
			pages.secondPage().tapLogin();
		}
	}

	public void goToRegister() {
		if (isElementPresent(AppiumBy.accessibilityId("welcome_continue_button"))) {
			pages.firstPage().tapContinue();
		}

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
		while (safetyCounter < 15) {
			// HomePage üzerinden buton durumunu kontrol ediyoruz
			int status = pages.homePage().getOnboardingStatus();

			if (status == 1) { // onboarding_next_button
				pages.homePage().clickOnboardingNext();
			} else if (status == 2) { // onboarding_done_button
				pages.homePage().clickOnboardingDone();
				break; // Son adım tıklandı, döngüden çık.
			} else {
				// Butonlar artık görünmüyorsa onboarding bitmiştir.
				break;
			}
			safetyCounter++;
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
}