public class NoStartException extends Exception {
    private static String line = "--------------------------------------------------------------------";
    public NoStartException() {
        super("(・´з`・) Uh oh... please add a start date\n" + line);
    }
}
