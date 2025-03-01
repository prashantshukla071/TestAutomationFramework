package Utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileReader {
    private static Properties properties;

    static {
        try {
        	String filePath = "";
//        	BufferedReader bf = new BufferedReader(new FileReader(filePath));
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties");
            properties = new Properties();
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file.");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
