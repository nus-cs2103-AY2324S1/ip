package duke.exception;

public class DukeNoTimeException extends DukeException{
    public DukeNoTimeException(String e) {
        super("    ____________________________________________________________\n" +
                "     ☹ OOPS!!! Please enter the correct duke.time format.\n" +
                "    ____________________________________________________________\n");
    }
}
