package jelly.commands;

import jelly.main.Storage;
import jelly.main.TaskList;
import jelly.main.Ui;
import jelly.task.Task;

/**
 * Deletes a task from the list.
 */
public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (index <= 0 || index > taskList.size()) {
            return ("Invalid input");
        }
        Task t = taskList.get(index - 1);
        taskList.delete(index - 1);
        storage.saveAndExit(taskList);
        return ui.showTaskDeleted(t, taskList.size());
    }
}
