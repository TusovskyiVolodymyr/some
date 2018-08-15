package utils;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.varia.FallbackErrorHandler;

import java.io.File;
import java.io.IOException;

public class Appender {

    public static void fileAppender(String filename) {
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.ALL);
        PatternLayout layout = new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n");
        File file = new File("logs\\" + filename + ".log");
        RollingFileAppender fileAppender = new RollingFileAppender();
        fileAppender.setLayout(layout);
        FallbackErrorHandler errorHandler = new FallbackErrorHandler();
        errorHandler.setAppender(fileAppender);
        errorHandler.setLogger(rootLogger);
        errorHandler.activateOptions();
        fileAppender.setErrorHandler(errorHandler);
        try {
            fileAppender.setFile(file.getAbsolutePath(), false, false, 100);
        } catch (IOException e) {
            e.printStackTrace();
        }
        rootLogger.addAppender(fileAppender);
    }
}
