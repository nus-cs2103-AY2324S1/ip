package haste.commands;

import haste.data.TaskList;
import haste.tasks.Task;
import haste.ui.Ui;

/**
 * Represents a command that marks a specific task as done.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Creates a MarkCommand.
     *
     * @param index The index of the task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Execute the command.
     *
     * @param tasks TaskList containing the tasks.
     * @param ui Ui that handles interactions.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        Task markedTask = tasks.getTask(index);
        markedTask.markDone();
        String taskDesc = markedTask.toString();
        ui.mark(taskDesc, tasks);
    }
}
