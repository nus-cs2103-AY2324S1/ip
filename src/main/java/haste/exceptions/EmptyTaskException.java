package haste.exceptions;

public class EmptyTaskException extends Exception {
    public EmptyTaskException(String task) {
        super("Description of " + task + " cannot be empty!");
    }

}
