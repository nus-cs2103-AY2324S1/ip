package duke.command;

import duke.UI.UI;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.taskList.Task;
import duke.taskList.TaskList;

import java.io.IOException;

public class Delete extends Command{

    public Delete(String s) {
        super(s);
    }

    @Override
    public void execute(TaskList lst, UI io, Storage storage) throws DukeException {
        int index = CommonMethods.getIndex(s);
        Task t = lst.delete(index);
        io.delete(t);
        try {
            storage.changeFile(lst);
        } catch (IOException iE){
            throw new DukeException(iE.getMessage());
        }
    }
}
