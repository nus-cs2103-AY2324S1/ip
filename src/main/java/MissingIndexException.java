/**
 * The MissingIndexException Class extends DukeException and is
 * used to denote that there is a missing parameter or index
 */
public class MissingIndexException extends DukeException{
    public MissingIndexException(String msg) {
        super(msg);
    }
}
