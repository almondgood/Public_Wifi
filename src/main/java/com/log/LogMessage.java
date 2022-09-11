package com.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogMessage extends LogMessageBase {
    private LogLevel logLevel;
    private String message;

    public LogMessage(LogLevel logLevel, String message) {
        super(logLevel);
        this.logLevel = logLevel;
        this.message = message;
    }

    @Override
    public String toString() {
        return fontColor + "[" + this.logLevel + "] " + "[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "] " + this.message + RESET;
    }
}
