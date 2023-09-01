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
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(index);
        task.unmarkDone();
        ui.unmarkDonePrint(task);
    }
}
