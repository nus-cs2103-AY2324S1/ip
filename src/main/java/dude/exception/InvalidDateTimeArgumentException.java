package dude.exception;

public class InvalidDateTimeArgumentException extends DudeException {
    public InvalidDateTimeArgumentException() {
        super(
                "Invalid date/time format. Check if the date and time is\n"
                        + "in the format: \"DD/MM/YYYY hhmm\"\n"
                        + "e.g. 31/12/2023 2359"
        );
    }
}
