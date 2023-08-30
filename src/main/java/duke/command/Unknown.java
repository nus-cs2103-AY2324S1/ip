package duke.command;

import duke.exception.DukeException;
import duke.taskList.TaskList;
import duke.UI.UI;
import duke.storage.Storage;

public class Unknown extends Command{
    public Unknown(String s) {
        super(s);
    }

    @Override
    public void execute(TaskList lst, UI io, Storage s) throws DukeException{
        throw new DukeException(super.s);
    }
}
