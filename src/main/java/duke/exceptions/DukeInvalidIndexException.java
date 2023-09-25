package duke.exceptions;

public class DukeInvalidIndexException extends DukeException{
    public DukeInvalidIndexException() {
        super("You don't have a task at that index. What are you trying to do?");
    }
}
