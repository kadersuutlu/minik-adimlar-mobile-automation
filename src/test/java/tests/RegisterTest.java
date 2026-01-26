package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import base.BaseTest;
import pages.RegisterPage;

public class RegisterTest extends BaseTest {

	@Test
	public void shouldNavigateToLoginAfterSuccessfulRegister() {

		System.out.println("Register test started...");

		RegisterPage registerPage = new RegisterPage(driver);
		
		String email = "test" + System.currentTimeMillis() + "@mail.com"; //Her defasında benzersiz email oluşturmak için

		registerPage.register("Test Kullanıcı", email, "5554443322", "Test123");
		
		assertTrue(
		        driver.findElements(
		            By.xpath("//android.widget.TextView[@text='Giriş Yap']")
		        ).size() > 0
		    );

		System.out.println("Register flow completed.");

	}
}