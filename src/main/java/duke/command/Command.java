package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

/**
 * An abstract class representing a command that can be executed in the main method.
 * Subclasses of this class define specific commands with their execution logic.
 */
public abstract class Command {

    /** The input string associated with the command in detail. */
    final String str;

    /**
     * Constructs a Command with the given input string.
     *
     * @param s The input string associated with the command in detail.
     */
    protected Command(String s) {
        this.str = s;
    }

    /**
     * Executes the command with the provided taskList, user interface, and storage.
     * Subclasses must implement this method to define the specific behavior of the command.
     *
     * @param lst The task list to operate on.
     * @param ui The user interface handling input and output.
     * @param storage The storage handler for reading and storing task data.
     * @throws DukeException If there is an error while executing the command.
     */
    public abstract String execute(TaskList lst, UI ui, Storage storage) throws DukeException;
}
