package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Task;

/**
 * The Command to indicate that the user wishes to delete a specific task from
 * the task list.
 */
public class DeleteCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String input = ui.getInput();
        String indexString = input.split(" ")[1];
        int num = Integer.valueOf(indexString);
        Task deletedTask = taskList.deleteTask(num);
        String str = ui.printDeleteTask(taskList, deletedTask);
        storage.writeTasks(taskList);
        return str;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
