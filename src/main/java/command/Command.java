package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;

import java.util.ArrayList;

public abstract class Command {
    /**
     * Parsed form of user input.
     */
    protected ArrayList<String> params;

    /**
     * Constructor for the Command class.
     *
     * @param params Parsed user input.
     */
    public Command(ArrayList<String> params) {
        this.params = params;
    }

    /**
     * Executes the command.
     *
     * @param tasks List of tasks.
     * @param ui UI of the application.
     * @param storage Object to handle data storage.
     * @throws DukeException If error is encountered during execution
     */
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws DukeException;

    /**
     * Returns a boolean representing whether the command requires the application to exit.
     *
     * @return Boolean representing whether the command exits the application.
     */
    public abstract boolean isExit();
}
