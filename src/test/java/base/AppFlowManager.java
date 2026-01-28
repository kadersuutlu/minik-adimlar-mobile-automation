package base;

import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;

public class AppFlowManager {

	AndroidDriver driver;

	public AppFlowManager(AndroidDriver driver) {
		this.driver = driver;
	}

	public void goToRegister() {

		// Onboarding 1
		if (driver.findElements(By.xpath("//android.widget.TextView[@text='Devam Et']")).size() > 0) {

			driver.findElement(By.xpath("//android.widget.TextView[@text='Devam Et']")).click();
		}

		// Onboarding 2
		if (driver.findElements(By.xpath("//android.widget.TextView[@text='Kayıt Ol']")).size() > 0) {

			driver.findElement(By.xpath("//android.widget.TextView[@text='Kayıt Ol']")).click();
		}
	}
}