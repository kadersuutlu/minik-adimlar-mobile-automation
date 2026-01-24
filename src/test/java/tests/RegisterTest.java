package tests;

import org.junit.jupiter.api.Test;

import base.BaseTest;
import pages.RegisterPage;

public class RegisterTest extends BaseTest {

	@Test
	public void testSuccessfulRegister() {

		System.out.println("Register test started...");

		RegisterPage registerPage = new RegisterPage(driver);

		registerPage.register("Test Kullanıcı", "test1@mail.com", "5554443322", "Test123");

		System.out.println("Register flow completed.");

	}

	@Test
	public void shouldNavigateToLoginAfterSuccessfulRegister() {

	}

	@Test
	public void shouldNotProceesWhenEmailIsInvalid() {

	}

	@Test
	public void shouldShowErrorWhenAgreementsNotAccepted() {

	}

	@Test
	public void shouldDisableContinueButtonWhenRequiredFieldsEmpty() {

	}
}