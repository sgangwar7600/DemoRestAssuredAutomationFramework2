package config;


import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop = new Properties();

    static {
        try {

            String env = System.getProperty("env", "qa");

            String fileName = "config-" + env + ".properties";

            InputStream input =
                    ConfigReader.class.getClassLoader()
                            .getResourceAsStream(fileName);

            if (input == null) {
                throw new RuntimeException("Config file not found: " + fileName);
            }

            prop.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config file");
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }
}

