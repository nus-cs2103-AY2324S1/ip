package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command for finding tasks.
 */
public class FindCommand extends Command {
    /** Represents a keyword. */
    private final String keyword;

    /**
     * Constructor method.
     *
     * @param input User input.
     * @throws DukeException If any error occurs.
     */
    public FindCommand(String input) throws DukeException {
        if (input == null) {
            throw new DukeException(" ☹ OOPS!!! What exactly are you finding?");
        }
        this.keyword = input.strip();
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
        ui.showMessage(String.format("Here are the matching tasks in your list:"
                + "%s", taskList.findTasks(keyword)));
    }

    @Override
    public String executeGui(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return String.format("Here are the matching tasks in your list:"
                + "%s", taskList.findTasks(keyword));
    }
}
