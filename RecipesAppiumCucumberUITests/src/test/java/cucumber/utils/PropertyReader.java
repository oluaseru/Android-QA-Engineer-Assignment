package cucumber.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    public PropertyReader() {
        loadProperties();
    }

    private Properties properties = new Properties();
    private InputStream inputStream = null;
    //check path to the config file is correct else, null values will occur
    private String path = "src/test/java/cucumber/config/config.properties";

    private void loadProperties() {
        try {
            inputStream = new FileInputStream(path);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readProperty(String key) {
        return properties.getProperty(key);
    }
}
