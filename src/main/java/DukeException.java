// package main.java;

public class DukeException extends Exception {
    DukeException(String message) {
        super(message);
    }

    public String toString() {
        return " ☹ OOPS!!! " + getMessage();
    }
}
