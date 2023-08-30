package duke;

public class DukeException extends Exception {
    /**
     * Creates the DukeException with the given message msg.
     *
     * @param msg The exception message for the DukeException.
     */
    public DukeException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return "\t____________________________________________________________\n"
                + "\t ☹ OOPS!!! " + this.getMessage() + "\n"
                + "\t____________________________________________________________\n";
    }
}
