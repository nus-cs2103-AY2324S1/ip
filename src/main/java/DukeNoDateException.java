public class DukeNoDateException extends DukeException{
    public DukeNoDateException(String msg) {
        super("there is no specific/accurate date for "
                + msg
                + "\n");
    }
}
