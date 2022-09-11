package com.log;


import static com.log.LogLevel.*;

public class Logging {
    public static void info(String message) {
        LogMessage logMessage = new LogMessage(INFO, message);
        System.out.println(logMessage);

    }

    public static void debug(String message) {
        LogMessage logMessage = new LogMessage(DEBUG, message);
        System.out.println(logMessage);
    }

    public static void error(String message) {
        LogMessage logMessage = new LogMessage(ERROR, message);
        System.out.println(logMessage);
    }

    public static void currentMethodError(Exception e) {
        StringBuilder sb = new StringBuilder();
//        sb.append(Thread.currentThread().getStackTrace()[2].getMethodName())
//                .append(" 메소드 에서 오류가 발생했습니다.");
//        LogMessage logMessage = new LogMessage(ERROR, sb.toString());
//        System.out.println(logMessage);

        sb = new StringBuilder();
        sb.append("Error : ")
                .append(e.getMessage());

        LogMessage logMessage = new LogMessage(ERROR, sb.toString());
        System.out.println(logMessage);
    }
}
