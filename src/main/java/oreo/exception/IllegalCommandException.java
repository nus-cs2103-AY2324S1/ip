package oreo.exception;

import java.util.NoSuchElementException;

public class IllegalCommandException extends NoSuchElementException {
    private String cmsg;

    public IllegalCommandException(String cmsg) {
        this.cmsg = cmsg;
    }

    @Override
    public String getMessage() {
        String message = "I dont think I can " +
                cmsg + "\n" +
                "do you want to try again?";
        return message;
    }
}
