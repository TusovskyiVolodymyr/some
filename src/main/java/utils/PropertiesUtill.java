package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtill {

    private Properties properties;

    public PropertiesUtill() throws IOException {
        try(FileInputStream in = new FileInputStream(new File("login.properties"))) {
            properties = new Properties();
            properties.load(in);
        }
    }
    public String getBaseUrl(){
        return properties.getProperty("base.url");
    }
    // if param

}
