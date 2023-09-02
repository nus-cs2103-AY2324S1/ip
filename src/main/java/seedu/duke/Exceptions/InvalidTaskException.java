package seedu.duke.Exceptions;

public class InvalidTaskException extends LemonException {
    public InvalidTaskException(String message) {
        super(":( OPPS!!! I'm sorry, but I don't know what"  + message + "means :-( \n Please add todo/ deadline / event before your task description~ or other commands like mark, unmark & delete");
    }

}
