import java.util.List;

public class AdditionalFieldException extends DukeException {
    public AdditionalFieldException(String cmd, String fields) {
        super(String.format("☹ OOPS!!! %s command should not have %s.", cmd, fields));
    }
}