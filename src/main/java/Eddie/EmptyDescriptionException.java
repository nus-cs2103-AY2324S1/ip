package Eddie;

/**
 * Exception for when user does not give a task description.
 */
public class EmptyDescriptionException extends DukeException{
    public EmptyDescriptionException() {
        super("The description cannot be empty... cmon bruh");
    }
}
