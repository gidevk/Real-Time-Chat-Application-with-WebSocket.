package com.chatapp.chatapp.Utils.LoggerUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.PrintStream;

public class CaLogger {

    public static final Logger logs = LogManager.getLogger("CaLogs");

    public static void redirectSystemOutToLogger() {
        System.setOut(new PrintStream(System.out) {
            @Override
            public void println(String x) {
                logs.info("[SYSOUT] {}", x);
            }
        });
    }
}
