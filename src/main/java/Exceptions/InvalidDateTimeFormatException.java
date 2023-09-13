package Exceptions;

public class InvalidDateTimeFormatException extends DukeException {
    public InvalidDateTimeFormatException() {
        super("Invalid DateTime format. Here are some example dates:\n"
                + "6/3/2023 5:30 AM, 16/12/2024 6:30PM");
    }
}
