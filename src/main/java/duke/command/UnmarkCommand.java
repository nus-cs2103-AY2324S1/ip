package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to unmark a Task object.
 */
public class UnmarkCommand extends Command {
    /** Index of Task object to be unmarked. */
    private final int index;

    /**
     * Constructor method.
     *
     * @param input User input.
     * @throws DukeException If any error occurs.
     */
    public UnmarkCommand(String input) throws DukeException {
        if (input == null) {
            throw new DukeException(" ☹ Which task?");
        }
        this.index = Integer.parseInt(input.strip());
    }

    /**
     * {@inheritDoc}
     *
     * @param taskList List of Task objects.
     * @param ui UI that the user interact with.
     * @param storage Storage to handle data to and from an external file.
     * @throws DukeException If any error occurs.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task unmarkedTask = taskList.unmarkTask(index);
        ui.showMessage(String.format("OK, I've marked this task as not done yet:\n    %s", unmarkedTask));
        storage.writeFile(taskList.stringToFile());
    }

    @Override
    public String executeGui(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task unmarkedTask = taskList.unmarkTask(index);
        storage.writeFile(taskList.stringToFile());
        return String.format("OK, I've marked this task as not done yet:\n    %s", unmarkedTask);
    }
}
