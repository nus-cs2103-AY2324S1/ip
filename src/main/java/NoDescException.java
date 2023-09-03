public class NoDescException extends DukeException {
    private static String line = "--------------------------------------------------------------------";
    public NoDescException() {
        super("(・´з`・) Uh oh... please add a description\n" + line);
    }
}
