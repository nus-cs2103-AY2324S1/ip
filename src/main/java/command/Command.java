package command;

import duke.DiskManager;
import duke.DukeException;
import duke.TaskManager;
import duke.Ui;


public abstract class Command {
    /**
     * Checks whether this command is an exit command.
     *
     * @return True if command is exit command.
     */
    public abstract boolean isExit();

    public abstract void execute(TaskManager taskManager, DiskManager diskManager, Ui ui) throws DukeException;
}
