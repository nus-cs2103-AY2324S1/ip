package pardiyem.command;

import pardiyem.storage.Storage;
import pardiyem.task.Task;
import pardiyem.task.TaskList;
import pardiyem.ui.Ui;

public class FindCommand extends Command {
    public FindCommand(String desc) {
        super(desc);
    }

    public boolean isExit() {
        return false;
    }

    public String execute(TaskList taskList, Storage storage) {
        return taskList.findAndList(desc);
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof FindCommand) {
            result = this.desc.equals(((FindCommand) obj).desc);
        }
        return result;
    }
}
