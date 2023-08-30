package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.Task;
import duke.tasklist.TaskList;
import duke.ui.UI;

public class Delete extends Command {

    public Delete(String s) {
        super(s);
    }

    @Override
    public void execute(TaskList lst, UI io, Storage storage) throws DukeException {
        int index = CommonMethods.getIndex(s);
        Task t = lst.delete(index);
        io.mark(t);
        try {
            storage.changeFile(lst);
        } catch (IOException iE) {
            throw new DukeException(iE.getMessage());
        }
    }
}
