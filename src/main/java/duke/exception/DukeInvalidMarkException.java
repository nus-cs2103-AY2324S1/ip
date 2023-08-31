package duke.exception;

public class DukeInvalidMarkException extends DukeException {
    public DukeInvalidMarkException(String e) {
        super("    ____________________________________________________________\n" +
                "     ☹ OOPS!!! The index for marking is invalid.\n" +
                "    ____________________________________________________________\n");

    }
}
