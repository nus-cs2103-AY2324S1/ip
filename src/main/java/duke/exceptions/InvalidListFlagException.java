package duke.exceptions;

public class InvalidListFlagException extends DukeException {

    public InvalidListFlagException() {
        super ("(・´з`・) Uh oh... please ensure format is 'list today/week/fort'");
    }
}