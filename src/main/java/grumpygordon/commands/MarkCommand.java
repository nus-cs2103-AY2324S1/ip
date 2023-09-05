package grumpygordon.commands;

import grumpygordon.storage.Storage;
import grumpygordon.tasks.TaskList;
import grumpygordon.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    /**
     * Index of the task to be marked as done.
     */
    private final int index;

    /**
     * Constructor of MarkCommand.
     * @param index Index of the task to be marked as done
     */
    public MarkCommand(int index) {
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
        tasks.markTaskAsDone(this.index);
        storage.saveTasks(tasks);
        return "Marked that task as done!\n" + tasks.getTask(this.index).toString();
    }
}
