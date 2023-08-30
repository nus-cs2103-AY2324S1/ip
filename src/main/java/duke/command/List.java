package duke.command;

import duke.UI.UI;
import duke.storage.Storage;
import duke.taskList.TaskList;

public class List extends Command {
    public List(String s) {
        super(s);
    }

    @Override
    public void execute(TaskList lst, UI io, Storage s) {
        io.list(lst);
    }
}
