package haste.commands;

import haste.data.TaskList;
import haste.ui.Ui;

/**
 * Represents a command that deletes a specific task
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates a DeleteCommand.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command.
     *
     * @param tasks TaskList containing the tasks.
     * @param ui Ui that handles interactions.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        String desc = tasks.getTask(index).toString();
        tasks.deleteTask(index);
        return ui.delete(desc, tasks);
    }
}
