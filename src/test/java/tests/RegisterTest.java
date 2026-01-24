package tests;

import base.BaseTest;
import pages.RegisterPage;

public class RegisterTest extends BaseTest {

	public void testSuccessfulRegister() {

		System.out.println("Register test started...");

		RegisterPage registerPage = new RegisterPage(driver);

		registerPage.register("Test Kullanıcı", "test1@mail.com", "5554443322");

		System.out.println("Register flow completed.");

	}
}