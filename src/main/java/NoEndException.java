public class NoEndException extends DukeException {
    private static String line = "--------------------------------------------------------------------";
    public NoEndException() {
        super("(・´з`・) Uh oh... please add an end date\n" + line);
    }
}
