package duke.command;

import duke.DukeList;
import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;

/**
 * The Command class represents an abstract command that can be executed.
 */
public abstract class Command {
    /**
     * The input command string.
     */
    protected final String command;

    /**
     * Constructs a new Command object with the specified command.
     *
     * @param command The input command string.
     */
    public Command(String command) {
        this.command = command;
    }

    /**
     * Executes the command.
     *
     * @param tasks   The DukeList containing the list of tasks.
     * @param ui      The Ui object for user interaction.
     * @param storage The Storage object for file storage.
     * @throws DukeException If there is an error during execution.
     */
    public abstract String execute(DukeList tasks, Ui ui, Storage storage) throws DukeException;
}
