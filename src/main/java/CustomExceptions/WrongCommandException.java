package CustomExceptions;
public class WrongCommandException extends Exception {
    public WrongCommandException(String command) {
        super(command + " is not a proper command! " +
                "\nPlease only use these following commands:\n" +
                "todo\n" +
                "deadline\n" +
                "event\n" +
                "mark\n" +
                "unmark\n" +
                "bye");
    }
}
