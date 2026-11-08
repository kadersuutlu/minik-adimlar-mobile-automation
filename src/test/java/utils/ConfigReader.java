package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            throw new RuntimeException("config.properties yüklenemedi: " + e.getMessage());
        }
    }

    public static String get(String key) {
        String value = find(key);
        if (value == null) {
            throw new RuntimeException("'" + key + "' bulunamadı. config.properties dosyasına ekleyin.");
        }
        return value;
    }

    // Test verisi sabitleri için: yerelde eksikse hata verir, CI'da boş döner
    public static String getLenientInCi(String key) {
        String value = find(key);
        if (value != null) {
            return value;
        }
        if (System.getenv("CI") != null) {
            return "";
        }
        return get(key);
    }

    private static String find(String key) {
        String value = System.getProperty(key);
        if (value == null || value.isBlank()) {
            value = System.getenv(key.toUpperCase().replace('.', '_'));
        }
        if (value == null || value.isBlank()) {
            value = properties.getProperty(key);
        }
        return (value == null || value.isBlank()) ? null : value;
    }
}