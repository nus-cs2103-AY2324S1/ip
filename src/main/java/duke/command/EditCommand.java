package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Represents a command to edit a task from the task list in the Duke application.
 */
public class EditCommand extends Command {
    private int index;
    private Task task;

    /**
     * Constructs an EditCommand object with the specified task to be edit.
     *
     * @param index The index of the task to be deleted.
     */
    public EditCommand(int index, Task task) {
        this.index = index;
        this.task = task;
    }

    /**
     * Executes the "edit" command, adding the stored task to the task list and updating storage.
     *
     * @param tasks   The task list that the command may operate on.
     * @param ui      The user interface to interact with the user or display messages.
     * @param storage The storage handler to manage data persistence.
     * @throws DukeException If an error occurs while executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.editTask(index, task, ui);
        storage.saveFile(tasks);
    }
}

