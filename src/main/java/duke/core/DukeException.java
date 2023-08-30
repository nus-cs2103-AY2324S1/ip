package duke.core;

public class DukeException extends Exception {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return String.format("%sError: %s%s", ANSI_RED, super.getMessage(), ANSI_RESET);
    }
}