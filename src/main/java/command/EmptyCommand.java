package command;

import duke.DiskManager;
import duke.DukeException;
import duke.TaskManager;

/**
 * Represents an empty command that does nothing.
 */
public class EmptyCommand extends Command {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskManager taskManager, DiskManager diskManager) throws DukeException{
        throw new DukeException("Oops!!! You forgot to input the command.");
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof EmptyCommand;
    }
}
