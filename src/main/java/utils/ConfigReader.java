package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop = new Properties();
    
    public static void loadProperties() {
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getProperty(String key) {
        return prop.getProperty(key);
    }


    public static String getUrl(String browser) {
        return prop.getProperty(browser.toLowerCase() + ".url");
    }
    
   
    public static String getUserName() {
        return prop.getProperty("UserName");
    }

    public static String getPassword() {
        return prop.getProperty("Password");
    }

}

