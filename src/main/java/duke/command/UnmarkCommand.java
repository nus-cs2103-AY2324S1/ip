package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents command Unmark which unmarks the task in the list.
 *
 * @author Armando Jovan Kusuma
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Creates an UnmarkCommand which unmarks the task with the specified index.
     *
     * @param index the specific index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the UnmarkCommand.
     *
     * @param tasks The TaskList where the command is to be executed.
     * @param ui The UI which functions as the user interface of the Chat bot.
     * @param storage The storage file to store the list of tasks.
     * @return a string indicating that a task has been unmarked.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getTaskCount() == 0) {
            return ui.errorPrint(new DukeException("The task list is empty. There are no tasks to unmark."));
        }

        if (index < 0 || index >= tasks.getTaskCount()) {
            return ui.errorPrint(new DukeException("OOPS! Please provide a valid task number to unmark"));
        }
        Task task = tasks.getTask(index);
        task.unmarkDone();
        return ui.unmarkDonePrint(task);
    }
}
