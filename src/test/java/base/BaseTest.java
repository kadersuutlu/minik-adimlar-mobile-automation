package base;

import java.net.URL;
import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class BaseTest {
	
	protected AndroidDriver driver;
	
	@BeforeEach
	public void setUp() throws Exception{
		
		UiAutomator2Options options = new UiAutomator2Options()
				.setDeviceName("Android Device")
                .setUdid("R5CX631QKAD")
                .setApp("C:/apk/minikadimlar.apk")
                .setAutoGrantPermissions(true)
                .setAndroidInstallTimeout(Duration.ofSeconds(180));
		
		driver=new AndroidDriver(
				new URL("http://127.0.0.1:4723"),
				options
				);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	

	@AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
