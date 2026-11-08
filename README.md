Minik Adımlar – Mobil Otomasyon Testleri
React Native ile geliştirilen ve backend tarafında Supabase kullanan Minik Adımlar mobil uygulamasının uçtan uca (E2E) test otomasyon projesi. Testler Appium 2.x ile Android üzerinde koşar; bazı akışlar için test verisi hazırlama ve temizleme işleri REST API üzerinden yapılır.
🎯 Test Kapsamı
14 test sınıfı, yaklaşık 80 test metodu:
Modül	Kapsam
Onboarding	Karşılama ekranları
Kayıt / Giriş	Geçerli ve geçersiz veri, şifre kuralları, hata mesajları
Şifre	Şifremi unuttum (geçerli, süresi dolmuş, kullanılmış token), şifre değiştirme
Bebek	İlk bebek ekleme, bebeklerim, bebek detayı
Ana sayfa / İçerik	Ana sayfa, içerikler, okuma listesi
Profil	Profil, kişisel bilgiler, hesap silme (onay ve iptal)
🛠 Kullanılan Teknolojiler
Dil: Java 17
Otomasyon: Appium 2.x (UiAutomator2), `io.appium:java-client`
Test framework: JUnit 5 (parametrik testler dahil)
API çağrıları: REST Assured
Raporlama: Allure Report
Build: Maven
CI: GitHub Actions
Backend: Supabase
🏗 Mimari
Page Object Model: Her ekran için `pages` paketinde bir sınıf; `PageManager` sayfaları tek yerden sağlar.
Akış yönetimi: `AppFlowManager`, birden çok ekrana yayılan ortak adımları (ör. girişe gitme) toplar.
Temel sınıflar: `BaseTest` driver yaşam döngüsünü, `BasePage` ortak element etkileşimlerini yönetir.
API katmanı: `api` paketi, test verisi hazırlamak için REST çağrılarını yapar.
Test verisi: Rastgele veri `TestData` ile üretilir; hesap bilgileri ve adresler koda değil ayar dosyasına konur.
Temizlik: `AccountCleanupUtil` ve `BabyCleanupUtil`, testlerin oluşturduğu kayıtları siler.
```
├── .github/workflows/appium-ci.yml   # CI (GitHub Actions)
├── src/test/
│   ├── java/
│   │   ├── api/      # REST çağrıları
│   │   ├── base/     # BaseTest, BasePage, PageManager, AppFlowManager
│   │   ├── data/     # TestData
│   │   ├── pages/    # Page Object sınıfları
│   │   ├── tests/    # JUnit 5 test senaryoları
│   │   └── utils/    # ConfigReader, temizlik yardımcıları
│   └── resources/
│       ├── allure.properties
│       └── config.properties.example
└── pom.xml
```
⚙️ Kurulum
Gereksinimler: JDK 17, Maven, Node.js, Appium 2.x ve UiAutomator2 sürücüsü, bir Android cihaz veya emulator.
Ayar dosyası: `src/test/resources/config.properties.example` dosyasını `config.properties` adıyla kopyalayıp doldurun. Bu dosya `.gitignore`'dadır, repoya gönderilmez.
Her ayar ayrıca JVM argümanı (`-Dapi.base.url=...`) veya ortam değişkeni (`API_BASE_URL`) olarak da verilebilir. Öncelik sırası: JVM argümanı → ortam değişkeni → `config.properties`.
APK: Dosya boyutu ve lisans nedeniyle repoda yoktur. Debug APK'yı yerelde temin edip yolunu verin.
🚀 Testleri Çalıştırma
```bash
# Appium sunucusunu başlatın
appium

# Gerçek cihazda
mvn clean test -Dapk.path=/yol/app-debug.apk -Ddevice.udid=<cihaz-udid>

# Emulator'de
mvn clean test -Dapk.path=/yol/app-debug.apk -Duse.emulator=true
```
Parametre	Varsayılan	Açıklama
`apk.path`	yerel yol	Test edilecek APK
`device.udid`	–	Gerçek cihazın UDID'si (`adb devices`)
`use.emulator`	`false`	`true` ise emulator ayarları kullanılır
📊 Raporlama (Allure)
```bash
mvn allure:serve
```
Sonuçlar `target/allure-results` klasörüne yazılır; testlerde `@Feature`, `@Story` ve `@Severity` etiketleri kullanılır.
🔄 CI
Her push ve pull request'te GitHub Actions projeyi derler ve testleri çalıştırır. CI ortamında emulator veya cihaz olmadığı için `BaseTest`, `CI` ortam değişkeni tanımlıysa UI testlerini bilinçli olarak atlar.
🔐 Gizli Bilgiler
Hesap bilgileri, şifreler ve API adresleri repoda tutulmaz. `config.properties` yalnızca yerelde bulunur; CI'da değerler GitHub Secrets ile ortam değişkeni olarak verilir.
📱 Platform Desteği
Platform	Sürücü	Durum
Android	UiAutomator2	✅ Aktif
iOS	XCUITest	📅 Planlanıyor
🗺️ Yol Haritası
[x] Faz 1 – Temel altyapı: Android (UiAutomator2) ve Page Object Model yapısı
[x] Faz 2 – CI: GitHub Actions ile derleme ve test pipeline'ı
[x] Faz 3 – Test kapsamı: Ana kullanıcı akışları ve hata senaryoları (devam eden genişletme)
[x] Faz 4 – Raporlama: Allure entegrasyonu
[ ] Faz 4 – Raporlama: Hata anında otomatik ekran görüntüsü
[x] Faz 6 – Test verisi temizliği: Testlerin oluşturduğu hesap ve bebek kayıtlarının silinmesi
[ ] Faz 5 – Multi-platform: iOS (XCUITest) desteği
[ ] Faz 6 – Optimizasyon: Paralel test koşumu
[ ] CI: Emulator ile gerçek test koşumu
---
> Bu çalışma test otomasyon becerilerimi sergilemek amacıyla paylaşılmıştır. Uygulamanın kaynak kodunu içermez.