package duke.command;

import duke.UI.UI;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.taskList.Task;
import duke.taskList.TaskList;

import java.io.IOException;

public class Remark extends Command {

    private final int state;
    public Remark(String s, int state) {
        super(s);
        this.state = state;
    }
    @Override
    public void execute(TaskList lst, UI io, Storage storage) throws DukeException {
        int index = CommonMethods.getIndex(s);
        if (state == 0) {
            Task t = lst.unmark(index);
            io.mark(t);
        } else {
            Task t = lst.mark(index);
            io.unmark(t);
        }
        try {
            storage.changeFile(lst);
        } catch (IOException ignored){
            throw new DukeException(ignored.getMessage());
        }
    }
}
