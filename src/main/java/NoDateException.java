public class NoDateException extends Exception {
    private static String line = "--------------------------------------------------------------------";
    public NoDateException() {
        super("(・´з`・) Uh oh... please add a deadline after '/by '\n" + line);
    }
}