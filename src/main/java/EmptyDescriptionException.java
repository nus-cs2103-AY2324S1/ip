/**
 * The EmptyDescriptionException Class extends DukeException and
 * is used to denote when a command has a missing description
 */
public class EmptyDescriptionException extends DukeException{
    public EmptyDescriptionException(String msg) {
        super(msg);
    }
}
