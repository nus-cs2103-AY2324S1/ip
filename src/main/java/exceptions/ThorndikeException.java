package exceptions;

public class ThorndikeException extends Exception {
    public ThorndikeException(String message) {
        super("MEOW! " + message);
    }
}