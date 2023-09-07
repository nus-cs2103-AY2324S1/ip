package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents command Mark which marks the task in the list.
 *
 * @author Armando Jovan Kusuma
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand with the specified index
     * of the task to be marked
     *
     * @param index The index of the task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the MarkCommand.
     *
     * @param tasks The TaskList where the command is to be executed.
     * @param ui The UI which functions as the user interface of the Chat bot.
     * @param storage The storage file to store the list of tasks.
     * @return a string indicating that a task has been marked.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(index);
        task.markDone();
        return ui.markDonePrint(task);
    }
}
