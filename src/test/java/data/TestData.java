package data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestData {

    public static String generateEmail() {
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("MMddHHmmss"));
        return "test_" + timestamp + "@test.com";
    }

    public static String generatePhoneNumber() {
        long randomPart = (long) (Math.random() * 900_000_000L) + 100_000_000L;
        return "5" + randomPart;
    }

    public static String generatePassword() {
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("MMddHHmmss"));
        return "Test_" + timestamp + "!";
    }

    public static String generateName() {
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("MMddHHmmss"));
        return "TestUser_" + timestamp;
    }

    // ===========================
    // Register Module
    // ===========================

    public static final String REG_VALID_PASSWORD = "Test1234!";
    public static final String REG_VALID_NAME = "Test User";
    public static final String REG_VALID_PHONE = "5551234567";

    public static final String REG_INVALID_EMAIL = "invalid_email";
    public static final String REG_EMPTY_PASSWORD = "";
    public static final String REG_SHORT_PASSWORD = "Abc12";
    public static final String REG_NO_UPPERCASE_PASSWORD = "test123";
    public static final String REG_NO_LOWERCASE_PASSWORD = "TEST123";
    public static final String REG_NO_SPECIAL_CHAR_PASSWORD = "Test1234";

    // ===========================
    // Login Module
    // ===========================

    public static final String LOG_USER_WITH_BABY_EMAIL = "kullanici_bebekli@test.com";
    public static final String LOG_USER_WITH_BABY_PASSWORD = "Test1234!";

    public static final String LOG_USER_WITHOUT_BABY_EMAIL = "kullanici_bebeksiz@test.com";
    public static final String LOG_USER_WITHOUT_BABY_PASSWORD = "Test1234!";

    public static final String LOG_INVALID_EMAIL_FORMAT = "invalid_email_format";
    public static final String LOG_WRONG_PASSWORD = "WrongPass1";
    public static final String LOG_EMPTY_EMAIL = "";
    public static final String LOG_EMPTY_PASSWORD = "";
    public static final String LOG_UNREGISTERED_EMAIL = "not_registered@test.com";

    // ===========================
    // Forgot Password Module
    // ===========================

    public static final String FP_VALID_EMAIL = "unique_user1@test.com";
    public static final String FP_INVALID_EMAIL = "invalid_email_format";
    public static final String FP_UNREGISTERED_EMAIL = "not_registered@test.com";

    public static final String FP_NEW_PASSWORD = "NewPass123";
    public static final String FP_PASSWORD_MISMATCH = "DiffPass123";
    public static final String FP_EMPTY_PASSWORD = "";

    public static final String FP_VALID_TOKEN = "valid_token";
    public static final String FP_EXPIRED_TOKEN = "expired_token";
    public static final String FP_USED_TOKEN = "used_token";

    // ===========================
    // Add First Baby Module
    // ===========================

    public static final String BABY_VALID_NAME = "Mihra";
    public static final String BABY_VALID_BIRTHDATE = "17.02.2024";
    public static final String BABY_VALID_RELATIONSHIP_MOTHER = "Anne";

    public static final String BABY_EMPTY_NAME = "";
    public static final String BABY_EMPTY_BIRTHDATE = "";

    // ===========================
    // Profile Module
    // ===========================

    public static final String DELETE_CANCEL_TEST_EMAIL = "delete_cancel_test@test.com";
    public static final String DELETE_CANCEL_TEST_PASSWORD = "Test123!";
    public static final String PRIVACY_POLICY_URL = "https://minikadimlar.site/gizlilik-politikasi.html";
    public static final String USER_AGREEMENT_URL = "https://minikadimlar.site/kullanim-kosullari.html";

    // ===========================
    // Personal Info Module
    // ===========================

    public static String PERSONAL_INFO_TEST_EMAIL = "personal_info_test@test.com";
    public static String PERSONAL_INFO_TEST_PASSWORD = "PersonalInfo123!";
}