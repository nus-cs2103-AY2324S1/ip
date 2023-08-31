package duke.command;
import duke.ui.Ui;
import duke.task.*;
import duke.storage.Storage;
import duke.DukeException;

/**
 * Represents an abstract command that can be executed.
 */
public abstract class Command {
    private String type;
    private String description;

    /**
     * Executes the command.
     *
     * @param list    The TaskList object managing the list of tasks.
     * @param ui      The Ui object to interact with the user interface.
     * @param storage The Storage object to manage data storage.
     * @throws DukeException If there is an issue executing the command.
     */
    public abstract void execute(TaskList list, Ui ui, Storage storage) throws DukeException;

    /**
     * Indicates whether this command is an exit command.
     *
     * @return True if this command is an exit command, false otherwise.
     */
    public abstract boolean isExit();
}
