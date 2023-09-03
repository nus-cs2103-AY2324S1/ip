package duke.exception;

public class EventDateTimeException extends Exception {
    public EventDateTimeException(String message) {
        super(message + ":" + "\n\tAccio error! I don't know what that means D: Please provide valid date inputs for events" +
                "in the yyyy-MM-dd HHmm format, and ensure that the start date & time of the " +
                "event does not occur after the end date & time of the event.");
    }
}
