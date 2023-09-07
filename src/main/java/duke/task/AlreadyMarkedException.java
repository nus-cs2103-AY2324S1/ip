package duke.task;

import duke.DukeException;

public class AlreadyMarkedException extends DukeException {
    public AlreadyMarkedException() {
        super("It's already done.");
    }
}
