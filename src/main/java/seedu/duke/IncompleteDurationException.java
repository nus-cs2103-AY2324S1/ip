package seedu.duke;

/**
 * Encapsulates the exception where event duration details are not inputted completely.
 */
 class IncompleteDurationException extends DukeException {
    public IncompleteDurationException() {
        super("____________________________________________________________\n" +
                " ☹ OOPS!!! Duration is not specified correctly.\n" +
                "____________________________________________________________");
    }
}
