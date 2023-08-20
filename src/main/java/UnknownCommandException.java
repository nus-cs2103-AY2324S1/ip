/**
 * The UnknownCommandException extends DukeException and is used
 * to denote that Duke does not understand that particular command
 */
public class UnknownCommandException extends DukeException{
    public UnknownCommandException(String msg) {
        super(msg);
    }
}
