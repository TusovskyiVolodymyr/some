package utils;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class WebDriverProperties {

    private final static Logger log = Logger.getLogger(WebDriverProperties.class);

    private static Properties properties;

    private WebDriverProperties() {
    }

    public static String getProperty(String name) {
        if (properties == null) initProperties();
        if (!properties.isEmpty() && properties.containsKey(name)) {
            return properties.getProperty(name);
        } else throw new RuntimeException("No such key in file: " + name);
    }

    public static void initProperties() {
        properties = new Properties();
        List<File> fileList = null;
        try {
            log.info("Scanning directory for properties files ....");
            fileList = Files.list(Paths.get("src\\main\\resources\\testProperties"))
                    .filter(path -> path.toString().endsWith(".properties")).map(Path::toFile).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (File file : fileList) {
            try (FileInputStream in = new FileInputStream(file)) {
                properties.load(in);
                log.info("Loaded properties file: " + file.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info("All property files was loaded !!!");
    }
}
