package base;

import java.net.URL;
import java.time.Duration;

import org.junit.jupiter.api.*;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    protected static final String APP_PACKAGE = "com.juniors.minikadimlar";

    protected AndroidDriver driver;
    protected PageManager pages;
    protected AppFlowManager flow;

    @BeforeAll
    public void setUp() throws Exception {

        String apkPath = System.getProperty("apk.path", "C:/apk/minikadimlar.apk");
        String udid = System.getProperty("device.udid", "R5CX631QKAD");
        boolean useEmu = Boolean.parseBoolean(System.getProperty("use.emulator", "false"));

        // --- Option 1: Real Device Settings ---
        UiAutomator2Options realDeviceOptions = new UiAutomator2Options()
                .setDeviceName("Android Device")
                .setUdid(udid)
                .setApp(apkPath)
                .setAutoGrantPermissions(true)
                .setNoReset(false)
                .setAndroidInstallTimeout(Duration.ofSeconds(10));

        // --- Option 2: Emulator Settings ---
        UiAutomator2Options emulatorOptions = new UiAutomator2Options()
                .setDeviceName("Pixel_6")
                .setUdid("emulator-5554")
                .setApp(apkPath)
                .setAppPackage(APP_PACKAGE)
                .setAppWaitActivity("com.juniors.minikadimlar.MainActivity")
                .setAutoGrantPermissions(true)
                .setNoReset(false)
                .setAndroidInstallTimeout(Duration.ofSeconds(10));

        Assumptions.assumeTrue(System.getenv("CI") == null, "CI ortamında Appium testleri atlanıyor");

        UiAutomator2Options selectedOptions = useEmu ? emulatorOptions : realDeviceOptions;
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), selectedOptions);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        pages = new PageManager(driver);
        flow = new AppFlowManager(driver, pages);
    }

    @AfterAll
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
}