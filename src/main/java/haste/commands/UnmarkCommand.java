package haste.commands;

import haste.data.TaskList;
import haste.tasks.Task;
import haste.ui.Ui;

/**
 * Represents a command that marks a specific task as undone.
 */
public class UnmarkCommand extends Command{
    private int index;

    /**
     * Creates an UnmarkCommand
     *
     * @param index The index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command.
     *
     * @param tasks TaskList containing the tasks.
     * @param ui Ui that handles interactions.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        Task unmarkedTask = tasks.getTask(index);
        unmarkedTask.markUndone();
        String taskDesc = unmarkedTask.toString();
        ui.mark(taskDesc, tasks);
    }
}
