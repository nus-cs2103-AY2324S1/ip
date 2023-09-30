package jelly.commands;

import jelly.main.Storage;
import jelly.main.TaskList;
import jelly.main.Ui;
import jelly.task.Task;

/**
 * Deletes a task from the list.
 */
public class DeleteCommand extends Command {

    private final int INDEX;

    public DeleteCommand(int index) {
        INDEX = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert INDEX > 0 : "Index should be positive.";
        if (INDEX <= 0 || INDEX > taskList.size()) {
            return ("Invalid input");
        }
        Task t = taskList.get(INDEX - 1);
        taskList.delete(INDEX - 1);
        storage.saveAndExit(taskList);
        return ui.showTaskDeleted(t, taskList.size());
    }
}
