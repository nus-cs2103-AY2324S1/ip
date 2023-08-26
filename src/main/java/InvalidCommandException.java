public class InvalidCommandException extends Exception {
    private static String line = "--------------------------------------------------------------------";
    public InvalidCommandException() {
        super("Sorry, I don't understand what you mean ><\n" + line);
    }
}
