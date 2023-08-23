public class EmptyToDoException extends DukeException {
    public EmptyToDoException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! The description of a todo cannot be empty.\n" +
                "    ____________________________________________________________";
    }
}
