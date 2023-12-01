package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Task;

/**
 * The Command that indicates the user wishes to mark a specific task as done.
 */
public class MarkCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String input = ui.getInput();
        String indexString = input.split(" ")[1];
        int num = Integer.valueOf(indexString);
        Task task = taskList.markTask(num);
        String str = ui.printMarkedTask(task);
        storage.writeTasks(taskList);
        return str;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
