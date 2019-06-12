package utils;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.varia.FallbackErrorHandler;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Appender extends RollingFileAppender {
    private static PatternLayout layout;
    private static Map<String, String> stringMap = new HashMap<>();
    private static ReentrantReadWriteLock writeLock = new ReentrantReadWriteLock();
    private static RollingFileAppender fileAppender;

    public static void fileAppender(String filename, String thread) {
        String local = "new " + filename + ".log";
        stringMap.put(thread, local);
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.ALL);
        layout = new PatternLayout("%t%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n");
        if (fileAppender == null) {
            fileAppender = new Appender();
        }
        fileAppender.setLayout(layout);
        FallbackErrorHandler errorHandler = new FallbackErrorHandler();
        errorHandler.setAppender(fileAppender);
        errorHandler.setLogger(rootLogger);
        errorHandler.activateOptions();
        fileAppender.setErrorHandler(errorHandler);
        System.out.println("file appender: " + fileAppender.toString());
        rootLogger.addAppender(fileAppender);
    }

    @Override
    public void append(LoggingEvent event) {
        writeLock.writeLock().lock();
        String thread = Thread.currentThread().getName();
        try {
            FileWriter fileWriter = new FileWriter(new File(stringMap.get(thread)), true);
            String log = layout.format(event);
//            log = log.replace(thread, "");
            fileWriter.write(log);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        writeLock.writeLock().unlock();
        super.append(event);
    }
}
