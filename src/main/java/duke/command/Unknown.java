package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

public class Unknown extends Command {
    public Unknown(String s) {
        super(s);
    }

    @Override
    public void execute(TaskList lst, UI io, Storage s) throws DukeException {
        throw new DukeException(super.s);
    }
}
