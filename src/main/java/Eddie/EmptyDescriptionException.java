package Eddie;

public class EmptyDescriptionException extends DukeException{
    public EmptyDescriptionException() {
        super("The description cannot be empty... cmon bruh");
    }
}
