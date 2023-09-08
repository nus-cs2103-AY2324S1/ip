package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents command Delete that deletes a task from the list
 *
 * @author Armando Jovan Kusuma
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand with the specified index
     * of the task to be deleted
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the DeleteCommand.
     *
     * @param tasks   The TaskList where the command is to be executed.
     * @param ui      The UI which functions as the user interface of the Chat bot.
     * @param storage The storage file to store the list of tasks.
     * @return a string indicating that a task has been deleted.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getTaskCount() == 0) {
            return ui.errorPrint(new DukeException("The task list is empty. There are no tasks to delete."));
        }

        if (index < 0 || index >= tasks.getTaskCount()) {
            return ui.errorPrint(new DukeException("OOPS! Please provide a valid task number to delete"));
        }

        Task task = tasks.getTask(index);
        tasks.remove(index);
        return ui.deletePrint(task, tasks.getTaskCount());
    }
}

