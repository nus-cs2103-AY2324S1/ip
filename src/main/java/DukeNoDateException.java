public class DukeNoDateException extends Exception{
    public DukeNoDateException(String msg) {
        super("there is no specific/accurate date for "
                + msg
                + "\n"
                + "____________________________________________");
    }
}
