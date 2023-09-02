package joe.exceptions;

public class JoeIndexOutOfBoundsException extends JoeException {
    public JoeIndexOutOfBoundsException(int idx) {
        super("Task " + idx + " does not exist");
    }
}
