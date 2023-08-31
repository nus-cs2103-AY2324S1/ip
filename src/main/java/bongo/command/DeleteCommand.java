package bongo.command;

import bongo.task.TaskList;
import bongo.helper.BongoException;
import bongo.helper.Ui;
import bongo.helper.Storage;
import bongo.task.Task;

public class DeleteCommand extends Command {
    int taskIndex;

    /**
     * A constructor for a DeleteCommand, with a taskIndex.
     *
     * @param command Array of strings from user command.
     * @throws BongoException If task index is missing.
     */
    public DeleteCommand(String[] command) throws BongoException {
        if (command.length <= 1) {
            throw new BongoException("Please include the task index.");
        }
        this.taskIndex = Integer.parseInt(command[1]) - 1 ;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BongoException {
        if (tasks.getTotalTasks() == 0) {
            throw new BongoException("There are currently no tasks.");
        }
        if (taskIndex < 0 || taskIndex >= tasks.getTotalTasks()) {
            throw new BongoException("Task does not exist.");
        }
        Task taskToBeDeleted = tasks.getTask(taskIndex);
        tasks.deleteTask(taskIndex);
        ui.showDeleteTask(taskToBeDeleted, tasks.getTotalTasks());
        storage.edit(Storage.FileAction.DELETE_TASK, this.taskIndex + 1);
    }
}
