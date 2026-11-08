# Minik Adımlar – Mobil Test Otomasyonu

[![CI](https://github.com/kadersuutlu/minik-adimlar-mobile-automation/actions/workflows/appium-ci.yml/badge.svg)](https://github.com/kadersuutlu/minik-adimlar-mobile-automation/actions/workflows/appium-ci.yml)

React Native ile geliştirilen **Minik Adımlar** mobil uygulaması için yazılmış, Android üzerinde çalışan uçtan uca (E2E) test otomasyonu. Testler Appium 2.x ile uygulamayı gerçek kullanıcı gibi kullanır. Test verisi hazırlama ve temizleme işleri REST API üzerinden yapılır.

Testler **canlı (production) ortama** karşı çalışır. Testlerin oluşturduğu hesap ve bebek kayıtları test bitince silinir.

| | |
| --- | --- |
| Platform | Android (UiAutomator2) |
| Dil | Java 17 |
| Test framework | JUnit 5 |
| Otomasyon | Appium 2.x, `io.appium:java-client` |
| API çağrıları | REST Assured |
| Raporlama | Allure Report |
| Build | Maven |
| CI | GitHub Actions |

## Test Kapsamı

14 test sınıfı, 80'e yakın test:

| Modül | Kapsam |
| --- | --- |
| Onboarding | Karşılama ekranları |
| Kayıt ve giriş | Geçerli/geçersiz veri, şifre kuralları, hata mesajları |
| Şifre | Şifremi unuttum (geçerli, süresi dolmuş ve kullanılmış token), şifre değiştirme |
| Bebek | İlk bebek ekleme, bebeklerim, bebek detayı |
| Ana sayfa ve içerik | Ana sayfa, içerikler, okuma listesi |
| Profil | Profil, kişisel bilgiler, hesap silme (onay ve iptal) |

## Mimari

- **Page Object Model:** Her ekran için `pages` paketinde bir sınıf vardır. `PageManager` sayfaları tek yerden sağlar.
- **Akış yönetimi:** `AppFlowManager`, birden çok ekrana yayılan ortak adımları toplar (ör. girişe gitme).
- **Temel sınıflar:** `BaseTest` driver yaşam döngüsünü, `BasePage` ortak element işlemlerini yönetir.
- **API katmanı:** `api` paketi test verisi hazırlamak için REST çağrıları yapar.
- **Test verisi:** Rastgele veriler `TestData` ile üretilir. Hesap bilgileri ve adresler koda değil ayar dosyasına konur.
- **Temizlik:** `AccountCleanupUtil` ve `BabyCleanupUtil` testlerin oluşturduğu kayıtları siler.

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

## Kurulum

**Gereksinimler:** JDK 17, Maven, Node.js, Appium 2.x ve UiAutomator2 sürücüsü, bir Android cihaz veya emulator.

**1. Ayar dosyası.** `src/test/resources/config.properties.example` dosyasını aynı klasöre `config.properties` adıyla kopyalayıp doldurun. Bu dosya `.gitignore`'dadır, repoya gönderilmez.

| Anahtar | Açıklama |
| --- | --- |
| `api.base.url` | API adresi |
| `register.valid.password` | Kayıt testlerinde yeni hesap açarken kullanılan geçerli şifre |
| `user.with.baby.email` / `.password` | Bebeği olan test hesabı |
| `user.without.baby.email` / `.password` | Bebeği olmayan test hesabı |
| `forgot.valid.email` | Şifremi unuttum testi için kayıtlı hesap |
| `delete.cancel.email` / `.password` | Hesap silme iptal testi için hesap |

Her ayar ayrıca JVM argümanı (`-Dapi.base.url=...`) ya da ortam değişkeni (`API_BASE_URL`) olarak verilebilir. Öncelik sırası: JVM argümanı, ortam değişkeni, `config.properties`.

**2. APK.** Dosya boyutu ve lisans nedeniyle repoda yoktur. Debug APK'yı yerelde temin edip yolunu `-Dapk.path` ile verin.

## Testleri Çalıştırma

```bash
# Appium sunucusunu başlatın
appium

# Gerçek cihazda
mvn clean test -Dapk.path=/yol/app-debug.apk -Ddevice.udid=<cihaz-udid>

# Emulator'de
mvn clean test -Dapk.path=/yol/app-debug.apk -Duse.emulator=true
```

| Parametre | Açıklama |
| --- | --- |
| `apk.path` | Test edilecek APK'nın yolu |
| `device.udid` | Gerçek cihazın UDID'si (`adb devices` ile öğrenilir) |
| `use.emulator` | `true` ise emulator ayarları kullanılır (varsayılan `false`) |

## Raporlama

Sonuçlar `target/allure-results` klasörüne yazılır. Testlerde `@Feature`, `@Story` ve `@Severity` etiketleri kullanılır. Raporu açmak için:

```bash
mvn allure:serve
```

## CI

Her push ve pull request'te GitHub Actions projeyi derler ve testleri çalıştırır. CI ortamında emulator veya cihaz olmadığı için `BaseTest`, `CI` ortam değişkeni tanımlıysa UI testlerini bilinçli olarak atlar.

## Yol Haritası

- [x] Android (UiAutomator2) altyapısı ve Page Object Model
- [x] GitHub Actions ile CI
- [x] Ana kullanıcı akışları ve hata senaryoları (genişletilmeye devam ediyor)
- [x] Allure raporlama
- [x] Testlerin oluşturduğu hesap ve bebek kayıtlarının temizlenmesi
- [ ] Hata anında otomatik ekran görüntüsü
- [ ] Paralel test koşumu
- [ ] CI'da emulator ile gerçek test koşumu
- [ ] iOS (XCUITest) desteği

---

Bu çalışma test otomasyon becerilerimi sergilemek amacıyla paylaşılmıştır. Uygulamanın kaynak kodunu içermez.
