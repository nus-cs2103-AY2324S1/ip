public class NoStartException extends DukeException {
    private static String line = "--------------------------------------------------------------------";
    public NoStartException() {
        super("(・´з`・) Uh oh... please add a start date\n" + line);
    }
}
