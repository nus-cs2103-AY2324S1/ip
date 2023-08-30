package exceptions;

public class EmptyTaskException extends DukeException {
    String msg;
    public EmptyTaskException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! The description of a(n) " + this.msg + " cannot be empty.\n" +
                "    ____________________________________________________________";
    }
}
