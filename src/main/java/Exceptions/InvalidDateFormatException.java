package Exceptions;

public class InvalidDateFormatException extends DukeException{
    public InvalidDateFormatException() {
        super("Invalid date format! input dates for by/from/to using the following format : dd/MM/yyyy HH:mm");
    }
}
