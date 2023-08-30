package duke;

public class DukeException extends Exception {
    public DukeException(String msg) {
        super(msg);
    }

    public String toString() {
        return "\t____________________________________________________________\n"
                + "\t ☹ OOPS!!! " + this.getMessage() + "\n"
                + "\t____________________________________________________________\n";
    }
}
