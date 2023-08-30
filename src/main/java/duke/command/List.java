package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

public class List extends Command {
    public List(String s) {
        super(s);
    }

    @Override
    public void execute(TaskList lst, UI io, Storage s) {
        io.list(lst);
    }
}
