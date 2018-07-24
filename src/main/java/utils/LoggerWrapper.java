package utils;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerWrapper {

    private Logger log;

    private static LoggerWrapper loggerWrapper;

    private LoggerWrapper(Class aClass) {
        log = LogManager.getLogger(aClass);
    }

    public static LoggerWrapper getLogger(Class aClass) {
        return new LoggerWrapper(aClass);
    }

    @Step()
    public void info(String message) {
        log.info(message);
    }

    @Step()
    public void error(String message) {
        log.error(message);
    }
}
