package bongo.command;

import bongo.helper.BongoException;
import bongo.helper.Storage;
import bongo.helper.Ui;
import bongo.task.TaskList;

/**
 * A class for a UnmarkCommand.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * A constructor for a UnmarkCommand, with a taskIndex.
     *
     * @param command Array of strings from user command.
     * @throws BongoException If task index is missing.
     */
    public UnmarkCommand(String[] command) throws BongoException {
        if (command.length <= 1) {
            throw new BongoException("Please include the task index.");
        }
        this.taskIndex = Integer.parseInt(command[1]) - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BongoException {
        if (tasks.getTotalTasks() == 0) {
            throw new BongoException("There are currently no tasks.");
        }
        if (taskIndex < 0 || taskIndex >= tasks.getTotalTasks()) {
            throw new BongoException("Task does not exist.");
        }
        tasks.markTaskUndone(this.taskIndex);
        storage.edit(Storage.FileAction.UNMARK_TASK, this.taskIndex + 1);
        return ui.showTaskIsUndone(tasks.getTask(this.taskIndex));
    }
}
