package bongo.command;

import bongo.task.TaskList;
import bongo.helper.BongoException;
import bongo.helper.Ui;
import bongo.helper.Storage;

public class UnmarkCommand extends Command {
    int taskIndex;

    /**
     * A constructor for a UnmarkCommand, with a taskIndex.
     * @param taskIndex
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BongoException {
        if (tasks.getTotalTasks() == 0) {
            throw new BongoException("There are currently no tasks.");
        }
        if (taskIndex < 0 || taskIndex >= tasks.getTotalTasks()) {
            throw new BongoException("Task does not exist.");
        }
        tasks.markTaskUndone(this.taskIndex);
        ui.showTaskIsUndone(tasks.getTask(this.taskIndex));
        storage.edit(Storage.FileAction.UNMARK_TASK, this.taskIndex + 1);
    }
}
