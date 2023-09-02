package haste.commands;

import haste.data.TaskList;
import haste.exceptions.HasteException;
import haste.ui.Ui;

/**
 * Represents a command that is invalid.
 */
public class InvalidCommand extends Command {
    private String message;

    /**
     * Creates an InvalidCommand.
     *
     * @param message The error message to be printed.
     */
    public InvalidCommand(String message) {
        this.message = message;
    }

    /**
     * Executes the command.
     *
     * @param tasks TaskList containing the tasks.
     * @param ui Ui that handles interactions.
     * @throws HasteException Exception that contains the relevant error message.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws HasteException {
        throw new HasteException(message);
    }
}
