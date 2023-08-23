public class AdamException extends RuntimeException{
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
}
