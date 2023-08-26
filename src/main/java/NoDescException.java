public class NoDescException extends Exception {
    private static String line = "--------------------------------------------------------------------";
    public NoDescException() {
        super("(・´з`・) Uh oh... please add a description\n" + line);
    }
}
