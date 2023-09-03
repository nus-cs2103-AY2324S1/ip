public class InvalidCommandException extends DukeException {
    private static String line = "--------------------------------------------------------------------";
    public InvalidCommandException() {
        super("Sorry, I don't understand what you mean ><\n" + line);
    }
}
