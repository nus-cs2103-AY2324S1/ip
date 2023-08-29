package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String str = "Here are the tasks in your list:\n\t ";
        int count = 0;
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            str += count + 1 + "." + task.toString() + "\n\t ";
            count++;
        }
        ui.sendMessage(str.substring(0, str.length() - 3));
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
