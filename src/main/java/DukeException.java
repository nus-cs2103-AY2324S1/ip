// package main.java;

public class DukeException extends Exception {
    DukeException() {
        // super(message);
    }

    public String toString() {
        return " ☹ OOPS!!! " + getMessage();
    }
}
