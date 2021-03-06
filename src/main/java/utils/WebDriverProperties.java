package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class WebDriverProperties {

    private Properties properties;

    public WebDriverProperties() throws IOException {
        try (FileInputStream in = new FileInputStream(new File("src\\main\\resources\\webDriver.properties"))) {
            properties = new Properties();
            properties.load(in);
        }
    }

    public String getProperty(String name) {
        if (!properties.isEmpty() && properties.containsKey(name)) {
            return properties.getProperty(name);
        } else throw new RuntimeException("No such key in file: " + name);
    }
}
