package base;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import pages.OnboardingFirstPage;
import pages.OnboardingSecondPage;

public class AppFlowManager {

	AndroidDriver driver;

	public AppFlowManager(AndroidDriver driver) {
		this.driver = driver;
	}

	public void goToRegister() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Onboarding First Page kontrol
		if (driver.findElements(AppiumBy.accessibilityId("welcome_continue_button")).size() > 0) {

			OnboardingFirstPage firstPage = new OnboardingFirstPage(driver);
			firstPage.tapContinue();
		}

		// Onboarding Second Page (Auth Choice)
		if (driver.findElements(AppiumBy.accessibilityId("auth_choice_signup_button")).size() > 0) {

			OnboardingSecondPage secondPage = new OnboardingSecondPage(driver);
			secondPage.tapRegister();
		}
	}

}