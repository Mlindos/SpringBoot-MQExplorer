package com.mlindos.projects.mqObserver.peepingTom.component;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Logger {

    private static final SimpleDateFormat LOGGER_TIMESTAMP = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");

    public static void log(String data)
    {
        String className = Thread.currentThread().getStackTrace()[2].getClassName();

        // Remove the package info.
        if ( (className != null) && (className.lastIndexOf('.') != -1) )
            className = className.substring(className.lastIndexOf('.')+1);

        System.out.println(LOGGER_TIMESTAMP.format(new Date()) + " " + className+": " + Thread.currentThread().getStackTrace()[2].getMethodName()+": " + data);
    }

}
