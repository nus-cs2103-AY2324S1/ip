package jeo.command;

import jeo.exception.JeoException;
import jeo.storage.Storage;
import jeo.task.Task;
import jeo.task.TaskList;
import jeo.ui.Ui;

/**
 * Represents the Command Delete that deletes a task from the list.
 *
 * @author Joseph Oliver Lim
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand with the index of the task to be deleted.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        super(true);
        this.index = index;
    }

    /**
     * Executes the DeleteCommand.
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
        Task task = tasks.getTask(index);
        tasks.removeTask(index);
        return ui.deleteTask(task, tasks.getCountTasks());
    }
}
