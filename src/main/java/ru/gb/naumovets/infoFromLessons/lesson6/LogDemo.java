package ru.gb.naumovets.infoFromLessons.lesson6;

import lombok.extern.java.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class LogDemo {

    static DateFormat format = new SimpleDateFormat("dd.mm.yyyy: HH:mm:ss");

    public static void main(String[] args) {
        //Логирование в консоль
        Logger log = Logger.getLogger(LogDemo.class.getName());
//        log.info("Log сообщение");
//        log.log(Level.WARNING,"Warning лог");

        //Логирование в консоль в определенном формате
        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.WARNING); //реагирует когда приходит лог с уровнем WARNING
        consoleHandler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return record.getLevel() + " " + format.format(Date.from(record.getInstant())) + " " + record.getSourceClassName() + " " + record.getSourceMethodName() + " " + record.getMessage();
            }
        });

        log.addHandler(consoleHandler);

        log.info("Log сообщение");
        log.log(Level.WARNING,"Warning лог");

    }
}
