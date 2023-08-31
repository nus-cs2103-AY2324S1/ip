package duke.exception;

public class DukeIndexOutOfBoundException extends DukeException{
    public DukeIndexOutOfBoundException(String e) {
        super("    ____________________________________________________________\n" +
                "     ☹ OOPS!!! The description cannot be empty.\n" +
                "    ____________________________________________________________\n");
    }
}
