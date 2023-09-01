package seedu.duke;

/**
 * Encapsulates the exception where the event start and end dates are not correct dates.
 */
public class InvalidDurationException extends DukeException {
    /**
     * Creates an InvalidDurationException.
     *
     * @param duration The duration given by the user.
     */
    public InvalidDurationException(String duration) {
        super("____________________________________________________________\n" +
                " ☹ OOPS!!! " + duration + " is not a valid duration.\n" +
                "Please input the duration in the following format:\n" +
                "/from DD-MM-YYYY TTTT /to DD-MM-YYYY TTTT\n" +
                "____________________________________________________________");
    }
}
