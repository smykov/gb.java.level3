package lesson6;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Slf4jDemo {

    // Simple logging facade for java

    /*
     *  ERROR
     *  WARNING
     *  INFO
     *  DEBUG
     *  TRACE
     */

    // Users/chestnov/.m2 maven local repository

    // app --log--> console <--logstash--> [elastic]

    public static void main(String[] args) {
        log.error("Error message");
        log.warn("Warning message");
        log.info("Info message");
        log.debug("Debug message");
        log.trace("Trace message");
    }
}