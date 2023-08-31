package Exceptions;

public class InvalidDateFormatException extends DukeException {
    public InvalidDateFormatException() {
        super("Invalid Date format. Here are some example dates:\n"
                + "6/3/2023, 16/12/2024");
    }
}
