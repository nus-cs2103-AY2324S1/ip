/**
 * This exception is a subclass of the AdamException and is used when a user didnt input the correct amount
 * of numbers after the command
 */
public class NumberException extends AdamException{
    public NumberException(){}
    public NumberException(String message){
        super(message);
    }
    public NumberException(Throwable cause){
        super(cause);
    }

    public NumberException(String message, Throwable cause){
        super(message,cause);
    }
    public String getInfo() {
        return "OOPS!!! You need to follow this command by a number";
    }
}
