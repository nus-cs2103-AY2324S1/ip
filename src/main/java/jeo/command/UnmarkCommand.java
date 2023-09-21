package jeo.command;

import jeo.exception.JeoException;
import jeo.storage.Storage;
import jeo.task.Task;
import jeo.task.TaskList;
import jeo.ui.Ui;

/**
 * Represents the Command Unmark that marks a task as not done yet.
 *
 * @author Joseph Oliver Lim
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand with the index of the task to be marked as not done yet.
     *
     * @param index The index of the task to be marked as not done yet.
     */
    public UnmarkCommand(int index) {
        super(true);
        this.index = index;
    }

    /**
     * Executes the UnmarkCommand.
     *
     * @param tasks The TaskList where the command is to be executed.
     * @param ui The Ui that functions as user interface.
     * @param storage The Storage that functions to store data.
     * @return A String to be shown to the user after the command is executed.
     * @throws JeoException If the index number is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JeoException {
        boolean validIndex = (index >= 0) && (index < tasks.getCountTasks());
        if (!validIndex) {
            throw new JeoException("The index number is invalid!");
        }
        Task task = tasks.getTask(this.index);
        task.unmarkAsDone();
        return ui.unmarkAsDone(task);
    }
}
