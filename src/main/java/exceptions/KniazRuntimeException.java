package exceptions;

public class KniazRuntimeException extends RuntimeException{

    private String userMessage = "";
    public KniazRuntimeException(String message, String userMessage, Throwable cause) {
        super(message,cause);
        this.userMessage = userMessage;

    }

    public String getUserMessage() {
        return userMessage;
    }
}
