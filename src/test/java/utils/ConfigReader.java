package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties bulunamadı");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("config.properties yüklenemedi: " + e.getMessage());
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("config.properties içinde '" + key + "' bulunamadı");
        }
        return value;
    }
}