package hachi.exceptions;

/**
 * Exception thrown when the event date format is given wrongly.
 */
public class EventDateException extends HachiException {
    /**
     * Constructor for the EventDateException class.
     * @param missing A string that specifies which dates are missing, start date, end date, or both.
     */
    public EventDateException(String missing) {
        super(String.format("☹ OOPS!!! Missing %s. Please input using this format: "
                        + "\"event <description> /from <start date> /to <end date>\"", missing));
    }
}
