package rua.common;

public class StringLogger {
    static public String log = "";

    static public void append(String str) {
        log = log + str;
    }

    static public void clear() {
        log = "";
    }

    static public String getLog() {
        return log;
    }
}
