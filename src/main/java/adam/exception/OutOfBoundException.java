package adam.exception;

/**
 * This exception is used when a edit command is used to edit something outside of the list.
 */
public class OutOfBoundException extends AdamException {
    @Override
    public String getInfo() {
        return "OOPS!!! The number you put in is more than the current item in your list";
    }
}
