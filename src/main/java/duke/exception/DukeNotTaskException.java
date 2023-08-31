package duke.exception;

public class DukeNotTaskException extends DukeException{
    public DukeNotTaskException(String e) {
        super("    ____________________________________________________________\n" +
                "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                "    ____________________________________________________________\n");
    }
}
