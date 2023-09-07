package duke.task;

import duke.DukeException;

public class AlreadyUnmarkedException extends DukeException {
    public AlreadyUnmarkedException() {
        super("This hasn't been done yet...");
    }
}
