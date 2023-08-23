public class EmptyTaskException extends DukeException {
    public EmptyTaskException (String msg) {
        super("☹ OOPS!!! The description of a " + msg + " cannot be empty.");
    }
}
