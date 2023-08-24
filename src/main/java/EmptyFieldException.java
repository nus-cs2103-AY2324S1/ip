import java.util.List;

public class EmptyFieldException extends DukeException {
    public EmptyFieldException(String cmd, String fields) {
        super(String.format("☹ OOPS!!! %s command should not have empty %s.", cmd, fields));
    }
}