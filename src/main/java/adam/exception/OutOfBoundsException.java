package adam.exception;

/**
 * This adam.exception is a subclass of the Exception.AdamException and is used when a user tried to edit a Tasks.Task that doesnt exist
 */
public class OutOfBoundsException extends AdamException {

    public OutOfBoundsException(){}
    public OutOfBoundsException(String message){
        super(message);
    }
    public OutOfBoundsException(Throwable cause){
        super(cause);
    }

    public OutOfBoundsException(String message, Throwable cause){
        super(message,cause);
    }
    public String getInfo() {
        return "OOPS!!! The number you put in is more than the current item in your list";
    }
}
