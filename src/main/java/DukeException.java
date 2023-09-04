public class DukeException extends Exception {
    private static String line = "--------------------------------------------------------------------";

    public DukeException() {
        super("(・´з`・) Uh oh... please add a start date\n" + line);
    }
    public DukeException(String message) {
        super(message);
    }
}