# Minik Adımlar – Mobil Otomasyon Testleri

Bu proje, React Native ile geliştirilen ve backend tarafında Supabase kullanan **Minik Adımlar** mobil uygulamasının fonksiyonel test otomasyon altyapısını içerir.

## 📌 Proje Hakkında
Uygulamanın uçtan uca (E2E) kullanıcı senaryolarını ve regresyon testlerini Appium 2.x kullanarak otomatize eder. Testler Android platformu üzerinde koşturulmaktadır.

## 🎯 Test Kapsamı
* **Auth:** Supabase tabanlı kayıt ve giriş işlemleri.
* **Data:** CRUD (Oluştur, Oku, Güncelle, Sil) fonksiyonlarının doğrulanması.
* **UI/UX:** Sayfa geçişleri, form validasyonları ve element etkileşimleri.
* **Synchronization:** Backend verilerinin UI üzerindeki tutarlılığı.

## 🛠 Kullanılan Teknolojiler
* **Dil:** Java 17+
* **Otomasyon:** Appium 2.x (UiAutomator2)
* **Framework:** TestNG
* **Build:** Maven
* **Backend:** Supabase

## 📂 Proje Yapısı
```plaintext
├── src
│   ├── main/java/pages       # Page Object sınıfları (Locators & Actions)
│   ├── test/java
│   │   ├── tests             # TestNG test senaryoları
│   │   └── utils             # DriverFactory, ConfigReader, Helpers
│   └── test/resources        # config.properties, test verileri
├── pom.xml                   # Bağımlılık yönetimi
└── app/                      # Test APK dosyası
```

## 🏗 Otomasyon Mimarisi

* **Page Object Model (POM):** UI elementleri ve iş mantığı (business logic) birbirinden izole edilmiştir.
* **Centralized Driver:** WebDriver yönetimi `DriverFactory` üzerinden singleton yapısıyla sağlanır.
* **Data Driven:** Test verileri harici dosyalardan (`.properties`) yönetilir.

## 📱 Platform Desteği

| Platform | Sürücü (Driver) | Durum |
| :--- | :--- | :--- |
| **Android** | UiAutomator2 | ✅ Aktif |
| **iOS** | XCUITest | 📅 Planlanıyor |

## 🚀 Testleri Çalıştırma

### Appium Sunucusu:
```bash
appium
```

### Test Koşumu:
```bash
mvn clean test
```

## 🗺️ Yol Haritası

- [x] **Faz 1: Temel Altyapı:** Android (UiAutomator2) mimarisinin kurulması, Page Object Model (POM) yapısının oluşturulması.
- [ ] **Faz 2: CI/CD Entegrasyonu:** GitHub Actions kullanarak temel bir pipeline oluşturulması (Kod push edildiğinde testlerin otomatik tetiklenmesi).
- [ ] **Faz 3: Test Kapsamı:** Temel kullanıcı akışlarının (Happy Path) ve kritik regresyon senaryolarının otomatize edilmesi.
- [ ] **Faz 4: Raporlama ve Analiz:** Allure Report entegrasyonu ile test sonuçlarının görselleştirilmesi ve hata anında otomatik ekran görüntüsü yakalama.
- [ ] **Faz 5: Multi-Platform:** iOS (XCUITest) desteğinin eklenmesi ve cross-platform test kabiliyetinin artırılması.
- [ ] **Faz 6: İleri Seviye Optimizasyon:** Paralel test koşumu ve Supabase üzerindeki test verilerinin otomatik temizlenmesi (Cleanup).

---
> **Not:** Bu çalışma sadece test otomasyon kurgusunu ve QA yetkinliklerini sergilemek amacıyla paylaşılmıştır. Projenin kaynak kodlarını veya hassas kullanıcı verilerini içermez.
