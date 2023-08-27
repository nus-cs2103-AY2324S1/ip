package bob.exception;

public class MissingEventDatesException extends BobException {
    public String message = "Start and end times must be provided for events!";
}
