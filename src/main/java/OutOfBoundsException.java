/**
 * This exception is a subclass of the AdamException and is used when a user tried to edit a Task that doesnt exist
 */
public class OutOfBoundsException extends AdamException{

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
