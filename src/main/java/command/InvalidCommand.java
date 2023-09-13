package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.InvalidCommandException;

/**
 * The Command to indicate that the user has type in an invalid command.
 */
public class InvalidCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidCommandException {
        throw new InvalidCommandException("Invalid command given. Please input a valid command.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
