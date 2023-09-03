package duke.commands;

import duke.DataStorage;
import duke.DukeException;
import duke.TaskList;
import duke.Ui;

/**
 * Abstract class for commands to be executed in application.
 */
public abstract class Command {

    /**
     * Executes command.
     *
     * @param tasks The TaskList containing ArrayList of tasks.
     * @param ui The UI handling user interactions.
     * @param store The DDataStorage handling data.
     * @throws DukeException If there is an error generated while command is run.
     */
    public abstract void execute(TaskList tasks, Ui ui, DataStorage store) throws DukeException;

    /**
     * Checks if the application should exit.
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }

}
