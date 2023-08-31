package duke.exception;

public class DukeInvalidDeleteException extends DukeException{
    public DukeInvalidDeleteException(String e) {
        super("    ____________________________________________________________\n" +
                "     ☹ OOPS!!! The index for deleting is invalid.\n" +
                "    ____________________________________________________________\n");

    }
}
