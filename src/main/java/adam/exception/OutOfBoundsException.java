package adam.exception;

/**
 * This exception is when you try to use an edit command outside of the array.
 */
public class OutOfBoundsException extends AdamException {
    @Override
    public String getInfo() {
        return "OOPS!!! The number you put in is more than the current item in your list";
    }
}
