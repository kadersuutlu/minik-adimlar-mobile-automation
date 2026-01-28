package base;

import java.net.URL;
import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import pages.OnboardingFirstPage;
import pages.OnboardingSecondPage;

public class BaseTest {

	protected AndroidDriver driver;
	protected AppFlowManager flow;

	@BeforeEach
	public void setUp() throws Exception {

		// --- Option 1: Real Device Settings ---
		UiAutomator2Options realDeviceOptions = new UiAutomator2Options().setDeviceName("Android Device")
				.setUdid("R5CX631QKAD").setApp("C:/apk/minikadimlar.apk").setAutoGrantPermissions(true)
				.setNoReset(false).setAndroidInstallTimeout(Duration.ofMinutes(10));

		// --- Option 2: Emulator Settings ---
		UiAutomator2Options emulatorOptions = new UiAutomator2Options().setDeviceName("Pixel_6")
				.setUdid("emulator-5554").setApp("C:/apk/minikadimlar.apk").setAppPackage("com.juniors.minikadimlar")
				.setAppWaitActivity("com.juniors.minikadimlar.MainActivity").setAutoGrantPermissions(true)
				.setNoReset(false).setAndroidInstallTimeout(Duration.ofMinutes(10));

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), emulatorOptions);

		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(10));

		flow = new AppFlowManager(driver);

		handleOnboarding();
	}

	@AfterEach
	public void tearDown() {
		try {
			if (driver != null) {
				driver.quit();
			}
		} catch (Exception e) {
			System.out.println("Driver quit sırasında hata oluştu: " + e.getMessage());
		}
		System.out.println("Test finished.");
	}

	protected void handleOnboarding() {
		new OnboardingFirstPage(driver).tapContinue();
		new OnboardingSecondPage(driver).tapRegister();
	}

}