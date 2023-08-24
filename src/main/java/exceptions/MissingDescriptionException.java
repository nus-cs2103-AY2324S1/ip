package exceptions;

public class MissingDescriptionException extends Exception{
    public MissingDescriptionException(String task) {
        super("OOPS!!! The description of a " + task + " cannot be empty.");
    }
}