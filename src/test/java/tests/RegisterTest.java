package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.BaseTest;
import io.appium.java_client.AppiumBy;

public class RegisterTest extends BaseTest {
	
	@Test
    public void testSuccessfulRegister() {
        System.out.println("Kayıt testi başlıyor...");

        // 1. Ad ve Soyad Girin
        WebElement nameField = driver.findElement(By.xpath("//android.widget.EditText[@text='Ad ve Soyadınızı Girin']"));
        nameField.sendKeys("Test Kullanıcı");

        // 2. E-Mail Adresinizi Girin
        WebElement emailField = driver.findElement(By.xpath("//android.widget.EditText[@text='E-Mail Adresinizi Girin']"));
        emailField.sendKeys("test1@mail.com");

        // 3. Telefon Numarasını Girin
        WebElement phoneField = driver.findElement(By.xpath("//android.widget.EditText[@text='+90 (5__) ___ __ __']"));
        phoneField.sendKeys("5554443322"); // Başına 0 koymadan veya maskeye uygun deneyebilirsin

        // 4. KVKK Onayı (Content-desc kullanarak)
        // Not: content-desc varsa AppiumBy.accessibilityId kullanmak en sağlıklısıdır
        driver.findElement(AppiumBy.accessibilityId("KVKK metnini okudum, onaylıyorum.*")).click();

        // 5. Kullanıcı Sözleşmesi Onayı
        driver.findElement(AppiumBy.accessibilityId("Kullanıcı Sözleşmesi'ni okudum, onaylıyorum.*")).click();

        // 6. Gizlilik Sözleşmesi Onayı
        driver.findElement(AppiumBy.accessibilityId("Gizlilik Sözleşmesi'ni okudum, onaylıyorum.*")).click();

        // 7. Devam Et Butonuna Tıkla
        driver.findElement(AppiumBy.accessibilityId("Devam Et")).click();

        System.out.println("Kayıt bilgileri girildi ve 'Devam Et' butonuna tıklandı.");
        
        // Buradan sonra eğer bir şifre belirleme ekranı geliyorsa 
        // veya ana sayfaya yönlendiriliyorsa oranın kontrolünü ekleyebilirsin.
    }

}
