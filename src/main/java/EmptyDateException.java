public class EmptyDateException extends DukeException{
    public EmptyDateException(String msg) {
        super("☹ OOPS!!! The date of a " + msg + " cannot be empty.");
    }
}
