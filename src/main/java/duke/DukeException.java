package duke;

public class DukeException extends Exception {

    public DukeException(String msg) {
        super(msg);
    }
    @Override
    public String getMessage() {
        return "OOPS!!! " + super.getMessage();
    }
}
