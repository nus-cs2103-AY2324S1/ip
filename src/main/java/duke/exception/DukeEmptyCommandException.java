package duke.exception;
public class DukeEmptyCommandException extends DukeException{
    public DukeEmptyCommandException() {

        super("A command is needed for the program to excute.");
    }
}
