package duke.command;

import java.util.Map;

import duke.core.DukeException;
import duke.core.Storage;
import duke.task.TaskList;

/**
 * Abstract class for all commands.
 */
public abstract class Command {
    /* Map to store additional parameters for execution of command. */
    protected Map<String, String> parameterMap;

    /**
     * Constructor for Command.
     */
    public Command(Map<String, String> parameterMap) {
        this.parameterMap = parameterMap;
    }

    public boolean isExit() {
        return false;
    }

    /**
     * Loads parameters for the command.
     */
    protected void loadParameters() throws DukeException {
        // No parameters to load
    }

    /**
     * Checks if all required parameters are specified.
     *
     * @throws DukeException If a required parameter is not specified.
     */
    protected void checkIfParametersSpecified() throws DukeException {
        // No parameters to check
    }

    /**
     * Checks if all parameters are valid.
     *
     * @throws DukeException If an invalid parameter is specified.
     */
    protected void checkIfParametersValid() throws DukeException {
        // No parameters to check
    }


    /**
     * Executes the command.
     *
     * @param tasks TaskList to be used by the command.
     * @param storage Storage to be used by the command.
     * @throws DukeException If an error occurs during execution of command.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;
}
