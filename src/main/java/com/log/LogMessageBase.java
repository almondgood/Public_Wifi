package com.log;


public abstract class LogMessageBase {

    protected static final String RESET = "\u001B[0m";
    private static final String FONT_BLACK = "\u001B[30m";
    private static final String FONT_RED = "\u001B[31m";
    private static final String FONT_GREEN = "\u001B[32m";
    private static final String FONT_YELLOW = "\u001B[33m";
    private static final String FONT_BLUE = "\u001B[34m";
    private static final String FONT_PURPLE = "\u001B[35m";
    private static final String FONT_CYAN = "\u001B[36m";
    private static final String FONT_WHITE = "\u001B[37m";
    private static final String BACKGROUND_BLACK = "\u001B[40m";
    private static final String BACKGROUND_RED = "\u001B[41m";
    private static final String BACKGROUND_GREEN = "\u001B[42m";
    private static final String BACKGROUND_YELLOW = "\u001B[43m";
    private static final String BACKGROUND_BLUE = "\u001B[44m";
    private static final String BACKGROUND_PURPLE = "\u001B[45m";
    private static final String BACKGROUND_CYAN = "\u001B[46m";
    private static final String BACKGROUND_WHITE = "\u001B[47m";

    protected String fontColor;
    public LogMessageBase(LogLevel logLevel) {
        fontColor = getFontColor(logLevel);
    }

    private String getFontColor(LogLevel logLevel) {
        if (logLevel == LogLevel.INFO) {
            return BACKGROUND_GREEN + FONT_BLACK;
        } else if(logLevel == LogLevel.ERROR) {
            return BACKGROUND_RED + FONT_BLACK;
        } else if(logLevel == LogLevel.DEBUG) {
            return BACKGROUND_BLUE + FONT_BLACK;
        } else {
            return RESET;
        }
    }
}
