package duke.exceptions;

import duke.exceptions.DukeException;

public class EmptyDetailsOfTaskError extends DukeException {
    public EmptyDetailsOfTaskError(String message) {
        super(message);
    }
}
