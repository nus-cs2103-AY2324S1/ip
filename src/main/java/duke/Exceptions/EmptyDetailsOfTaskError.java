package duke.Exceptions;

import duke.Exceptions.DukeException;

public class EmptyDetailsOfTaskError extends DukeException {
    public EmptyDetailsOfTaskError(String message) {
        super(message);
    }
}
