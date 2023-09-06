package chat.exceptions;

/**
 * @author juzzztinsoong
 */
public class ChatException extends Exception {

    private String errDescription;

    public ChatException(String errDescription) {
        this.errDescription = errDescription;
    }

    public String toString() {
        return "OOPS! " + errDescription;
    }

    public String getMessage() {
        return "OOPS! " + errDescription;
    }
}
