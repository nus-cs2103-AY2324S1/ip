package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Task;

/**
 * The Command which indicates the user wishes to mark a specific task in the
 * task list as incomplete.
 */
public class UnmarkCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String input = ui.getInput();
        String indexString = input.split(" ")[1];
        int num = Integer.valueOf(indexString);
        Task task = taskList.unmarkTask(num);
        String str = ui.printUnmarkedTask(task);
        storage.writeTasks(taskList);
        return str;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
