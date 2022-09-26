package ru.gb.naumovets.infoFromLessons.lesson6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class slf4jDemo {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger(slf4jDemo.class);
        log.error("Message info");
        log.warn("Message info");
        log.info("Message info");
        log.debug("Message info");
        log.trace("Message info");
    }
}
