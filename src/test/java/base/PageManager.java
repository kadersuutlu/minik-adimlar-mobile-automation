package base;

import io.appium.java_client.android.AndroidDriver;
import pages.AddBabyPage;
import pages.AddFirstBabyPage;
import pages.ChangePasswordPage;
import pages.ContentsPage;
import pages.ForgotPasswordPage;
import pages.HomePage;
import pages.LoginPage;
import pages.MyBabiesPage;
import pages.MyBabyPage;
import pages.NotificationPage;
import pages.OnboardingFirstPage;
import pages.OnboardingSecondPage;
import pages.PersonalInfoPage;
import pages.PrivacyPolicyPage;
import pages.ProfilePage;
import pages.ReadingListPage;
import pages.RegisterPage;
import pages.ScedulePage;
import pages.UserAgreementPage;

public class PageManager {
	private AndroidDriver driver;

	private RegisterPage registerPage;
	private LoginPage loginPage;
	private ForgotPasswordPage forgotPasswordPage;
	private AddFirstBabyPage addFirstBabyPage;
	private HomePage homePage;
	private ContentsPage contentsPage;
	private MyBabyPage myBabyPage;
	private NotificationPage notificationPage;
	private AddBabyPage addBabyPage;
	private ScedulePage scedulePage;
	private ReadingListPage readingListPage;
	private OnboardingFirstPage firstPage;
	private OnboardingSecondPage secondPage;
	private ProfilePage profilePage;
	private PersonalInfoPage personalInfoPage;
	private MyBabiesPage myBabiesPage;
	private ChangePasswordPage changePasswordPage;
	private UserAgreementPage userAgreementPage;
	private PrivacyPolicyPage privacyPolicyPage;

	public PageManager(AndroidDriver driver) {
		this.driver = driver;
	}

	public RegisterPage registerPage() {
		if (registerPage == null) {
			registerPage = new RegisterPage(driver);
		}
		return registerPage;
	}

	public LoginPage loginPage() {
		if (loginPage == null) {
			loginPage = new LoginPage(driver);
		}
		return loginPage;
	}

	public ForgotPasswordPage forgotPasswordPage() {
		if (forgotPasswordPage == null) {
			forgotPasswordPage = new ForgotPasswordPage(driver);
		}
		return forgotPasswordPage;
	}

	public AddFirstBabyPage addFirstBabyPage() {
		if (addFirstBabyPage == null) {
			addFirstBabyPage = new AddFirstBabyPage(driver);
		}
		return addFirstBabyPage;
	}

	public HomePage homePage() {
		if (homePage == null) {
			homePage = new HomePage(driver);
		}
		return homePage;
	}

	public ContentsPage contentsPage() {
		if (contentsPage == null) {
			contentsPage = new ContentsPage(driver);
		}
		return contentsPage;
	}

	public MyBabyPage myBabyPage() {
		if (myBabyPage == null) {
			myBabyPage = new MyBabyPage(driver);
		}
		return myBabyPage;
	}

	public NotificationPage notificationPage() {
		if (notificationPage == null) {
			notificationPage = new NotificationPage(driver);
		}
		return notificationPage;
	}

	public AddBabyPage addBabyPage() {
		if (addBabyPage == null) {
			addBabyPage = new AddBabyPage(driver);
		}
		return addBabyPage;
	}

	public ScedulePage scedulePage() {
		if (scedulePage == null) {
			scedulePage = new ScedulePage(driver);
		}
		return scedulePage;
	}

	public ReadingListPage readingListPage() {
		if (readingListPage == null) {
			readingListPage = new ReadingListPage(driver);
		}
		return readingListPage;
	}

	public OnboardingFirstPage firstPage() {
		if (firstPage == null) {
			firstPage = new OnboardingFirstPage(driver);
		}
		return firstPage;
	}

	public OnboardingSecondPage secondPage() {
		if (secondPage == null) {
			secondPage = new OnboardingSecondPage(driver);
		}
		return secondPage;
	}

	public ProfilePage profilePage() {
		if (profilePage == null) {
			profilePage = new ProfilePage(driver);
		}
		return profilePage;
	}

	public PersonalInfoPage personalInfoPage() {
		if (personalInfoPage == null) {
			personalInfoPage = new PersonalInfoPage(driver);
		}
		return personalInfoPage;
	}

	public MyBabiesPage myBabiesPage() {
		if (myBabiesPage == null) {
			myBabiesPage = new MyBabiesPage(driver);
		}
		return myBabiesPage;
	}

	public ChangePasswordPage changePasswordPage() {
		if (changePasswordPage == null) {
			changePasswordPage = new ChangePasswordPage(driver);
		}
		return changePasswordPage;
	}

	public UserAgreementPage userAgreementPage() {
		if (userAgreementPage == null) {
			userAgreementPage = new UserAgreementPage(driver);
		}
		return userAgreementPage;
	}

	public PrivacyPolicyPage privacyPolicyPage() {
		if (privacyPolicyPage == null) {
			privacyPolicyPage = new PrivacyPolicyPage(driver);
		}
		return privacyPolicyPage;
	}
}