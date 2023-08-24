public class InvalidDescriptionException extends Exception {
    public InvalidDescriptionException(String task) {
        super("☹ OOPS!!! The description of a " + task + " cannot be empty.");
    }
}
