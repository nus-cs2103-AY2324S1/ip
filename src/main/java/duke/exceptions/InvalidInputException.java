package duke.exceptions;

import duke.exceptions.DukeException;

public class InvalidInputException extends DukeException {
    public InvalidInputException(String message) {
        super(message);
    }
}