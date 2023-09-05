package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents a specific command to be executed.
 */
public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns whether the Command should exit the program when run.
     *
     * @return False.
     */
    public boolean isExit() {
        return false;
    }

    public abstract String getCommandType();
}
