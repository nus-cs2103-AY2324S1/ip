package grumpygordon.commands;

import grumpygordon.storage.Storage;
import grumpygordon.tasks.TaskList;
import grumpygordon.ui.Ui;

/**
 * Represents a command to mark a task as undone.
 */
public class UnmarkCommand extends Command {

    /**
     * Index of the task to be marked as undone.
     */
    private final int index;

    /**
     * Constructor of UnmarkCommand.
     * @param index Index of the task to be marked as undone
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command.
     * @param tasks The list of tasks
     * @param ui The user interface
     * @param storage The storage
     * @return The output string
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markTaskAsUndone(this.index);
        storage.saveTasks(tasks);
        return "Marked that task as undone!\n" + tasks.getTask(this.index).toString();
    }
}
