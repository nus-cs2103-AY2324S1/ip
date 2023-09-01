package max.commands;

import max.commands.*;
import max.exception.MaxException;
import max.parser.Parser;
import max.storage.Storage;
import max.tasks.TaskList;
import max.tasks.*;
import max.ui.Ui;

/**
 * Represents commands by user.
 */
public class Command {
    public Command() {

    }

    /**
     * Executes given command.
     *
     * @param tasks Task list
     * @param ui UI
     * @param storage Storage
     * @throws MaxException If command is invalid
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MaxException {
        throw new MaxException("This command cannot be executed bro.");
    }

    /**
     * Checks if command is an exit command.
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}
