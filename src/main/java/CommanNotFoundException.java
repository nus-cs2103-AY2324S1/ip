/**
 * Exception for input not included in ToDos, Deadlines, and Events
 */
public class CommanNotFoundException extends Exception {
    /**
     * The constructor
     */
    public CommanNotFoundException() {
        super("OOPS!!! I'm sorry, but I don't know what that means");
    }
}
