package duke.exceptions;

public class DukeException extends Exception{
    String msg;

    public DukeException(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return this.msg;
    }
}
