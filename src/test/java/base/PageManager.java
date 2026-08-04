package base;

import io.appium.java_client.android.AndroidDriver;
import pages.*;

public class PageManager {
    private AndroidDriver driver;

    private RegisterPage registerPage;
    private LoginPage loginPage;
    private ForgotPasswordPage forgotPasswordPage;
    private AddFirstBabyPage addFirstBabyPage;
    private HomePage homePage;
    private ContentsPage contentsPage;
    private MyBabyPage myBabyPage;
    private SleepTrackingPage sleepTrackingPage;
    private NutritionTrackingPage nutritionTrackingPage;
    private DiaperTrackingPage diaperTrackingPage;
    private GrowthTrackingPage growthTrackingPage;
    private HealthTrackingPage healthTrackingPage;
    private FirstTrackingPage firstTrackingPage;
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

    public SleepTrackingPage sleepTrackingPage() {
        if (sleepTrackingPage == null) {
            sleepTrackingPage = new SleepTrackingPage(driver);
        }
        return sleepTrackingPage;
    }

    public NutritionTrackingPage nutritionTrackingPage() {
        if (nutritionTrackingPage == null) {
            nutritionTrackingPage = new NutritionTrackingPage(driver);
        }
        return nutritionTrackingPage;
    }

    public DiaperTrackingPage diaperTrackingPage() {
        if (diaperTrackingPage == null) {
            diaperTrackingPage = new DiaperTrackingPage(driver);
        }
        return diaperTrackingPage;
    }

    public GrowthTrackingPage growthTrackingPage() {
        if (growthTrackingPage == null) {
            growthTrackingPage = new GrowthTrackingPage(driver);
        }
        return growthTrackingPage;
    }

    public HealthTrackingPage healthTrackingPage() {
        if (healthTrackingPage == null) {
            healthTrackingPage = new HealthTrackingPage(driver);
        }
        return healthTrackingPage;
    }

    public FirstTrackingPage firstTrackingPage() {
        if (firstTrackingPage == null) {
            firstTrackingPage = new FirstTrackingPage(driver);
        }
        return firstTrackingPage;
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