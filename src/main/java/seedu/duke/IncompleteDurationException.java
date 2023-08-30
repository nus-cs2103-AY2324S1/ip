package seedu.duke;
 class IncompleteDurationException extends DukeException {
    public IncompleteDurationException() {
        super("____________________________________________________________\n" +
                " ☹ OOPS!!! Deadline is not specified correctly.\n" +
                "____________________________________________________________");
    }
}
