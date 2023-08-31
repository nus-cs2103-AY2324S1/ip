package exception;

public class DetailsUnknownException extends ChattyException {

    public DetailsUnknownException() {

        super("The details are missing. Please check your input again");
    }
}
