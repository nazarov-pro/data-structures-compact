package org.shaheen.nazarov.utility.logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogBack {

    public static void logLevels() {
        log.trace("TRACE");
        log.info("INFO");
        log.debug("DEBUG");
        log.warn("WARN");
        log.error("ERROR");
    }

    public static void logFormat() {
        log.info("Hello {}", "world");

        for (int i = 0; i < 5; i++) {
            log.info("Hello {} i={}", "world", i);
        }
    }

    public static void conditionalLogging() {
        if (log.isInfoEnabled()) {
            Object expensiveCall = null;
            log.info("Logger expensive call {}", expensiveCall);
        }
    }

    public static void logException() {
        try {
            throw new RuntimeException("What happened?");
        } catch (Exception ex) {
            log.error("Something bad happened", ex.getLocalizedMessage());
        }
    }

    public static void main(String[] args) {
        logLevels();
        logException();
        conditionalLogging();
        logFormat();
    }
}
