package utils;

import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class LoggerWrapper {

    private Logger log;

    private  LoggerWrapper loggerWrapper;

    private LoggerWrapper(Class aClass) {
        log = LogManager.getLogger(aClass);
    }

    public static LoggerWrapper getLogger(Class aClass) {
        return new LoggerWrapper(aClass);
    }

    @Step(value = "{message}")
    public void info(String message) {
        log.info(message);
    }

    @Step(value = "{message}")
    public void error(String message) {
        log.error(message);
    }
}
