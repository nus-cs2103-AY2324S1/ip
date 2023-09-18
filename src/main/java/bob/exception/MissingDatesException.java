package bob.exception;

/**
 * Exception when dates are not provided or in wrong format
 * for Events and Deadlines
 */
public class MissingDatesException extends BobException {
    public String message = "Please provide the appropriate date.\n" +
            "For example:\n" + "For deadlines: /by 2022-01-01\n"
            + "For events: /from 2022-01-01 /to 2022-02-02";
}
