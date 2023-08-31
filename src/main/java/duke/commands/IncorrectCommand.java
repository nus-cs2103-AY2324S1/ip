package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.DukeList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a command to handle incorrect or invalid user input.
 */
public class IncorrectCommand extends Command {
    private String ErrMessage;

    /**
     * Constructs a command to handle incorrect input.
     *
     * @param ErrMessage The error message to be thrown.
     */
    public IncorrectCommand(String ErrMessage) {
        this.ErrMessage = ErrMessage;
    }

    /**
     * Executes the command to handle incorrect input by throwing a DukeException.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage manager.
     * @throws DukeException Always thrown with the error message provided during construction.
     */
    @Override
    public void execute(DukeList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(this.ErrMessage);
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return False, as this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
