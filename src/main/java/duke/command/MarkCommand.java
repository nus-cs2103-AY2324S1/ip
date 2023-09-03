package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to mark a Task object.
 */
public class MarkCommand extends Command {
    /** Index of command to be marked. */
    private final int index;

    /**
     * Constructor method.
     *
     * @param input User input.
     * @throws DukeException If any error occurs.
     */
    public MarkCommand(String input) throws DukeException {
        if (input == null) {
            throw new DukeException(" ☹ Which task?");
        }
        this.index = Integer.parseInt(input.strip());
    }

    /**
     * {@inheritDoc}
     * @param taskList List of Task objects.
     * @param ui UI that the user interact with.
     * @param storage Storage to handle data to and from an external file.
     * @throws DukeException If any error occurs.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task markedTask = taskList.markTask(index);
        ui.showMessage(String.format("Nice! I've marked this task as done:\n    %s", markedTask));
        storage.writeFile(taskList.stringToFile());
    }

    @Override
    public String executeGui(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task markedTask = taskList.markTask(index);
        storage.writeFile(taskList.stringToFile());
        return String.format("Nice! I've marked this task as done:\n    %s", markedTask);
    }
}
