package duke.command;

import duke.taskList.TaskList;
import duke.UI.UI;
import duke.storage.Storage;

public class Bye extends Command {
    public Bye(String s) {
        super(s);
    }

    @Override
    public void execute(TaskList lst, UI io, Storage s) {
        io.showMessage("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
