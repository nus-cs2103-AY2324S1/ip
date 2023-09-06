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

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList out = new TaskList();

        for (int i = 0; i < taskList.size(); i++) {
            Task curr = taskList.getTask(i);
            if (curr.getDescription().contains(desc)) {
               out.add(curr);
           }
        }

        ui.showOutput(out.toString());
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
