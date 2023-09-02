package duke.exceptions;

import duke.exceptions.DukeException;

public class IncompleteInputException extends DukeException {
    public IncompleteInputException(String message) {
        super(message);
    }
}