/**
 * This Exception is a subclass of a RunTimeException and is used when an unidentified user input was entered
 */
public class AdamException extends RuntimeException{
    /**
     * test
     */
    public AdamException(){}
    public AdamException(String message){
        super(message);
    }
    public AdamException(Throwable cause){
        super(cause);
    }

    public AdamException(String message, Throwable cause){
        super(message,cause);
    }

    public String getInfo() {
        return "OOPS!!! I don't know what this means";
    }
}
