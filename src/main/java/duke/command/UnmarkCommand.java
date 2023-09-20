package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to unmark a task.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand object with the specified task to be marked.
     *
     * @param index The index of the task to be marked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the "unmark" command, and unmarks the task.
     *
     * @param tasks   The task list that the command may operate on.
     * @param ui      The user interface to interact with the user or display messages.
     * @param storage The storage handler to manage data persistence.
     * @throws DukeException If an error occurs while executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.unmarkTask(index, ui);
        storage.saveFile(tasks);
    }
}

