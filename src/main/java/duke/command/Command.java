package duke.command;

import duke.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This abstract class represents command and becomes the parent
 * of all commands.
 */
public abstract class Command {
    /**
     * Returns a boolean to state whether the program terminates on command.
     * @return boolean whether the program terminates on command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * All command classes implement this execute function.
     * @param tasks
     * @param ui
     * @throws DukeException
     */
    public abstract void execute(TaskList tasks, Ui ui) throws DukeException;
}
