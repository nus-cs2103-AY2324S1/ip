package duke.command;

import java.util.Map;

import duke.core.DukeException;
import duke.core.Storage;
import duke.core.Ui;
import duke.task.TaskList;

/**
 * Abstract class for all commands.
 */
public abstract class Command {
    /* Map to store additional parameters for execution of command. */
    Map<String, String> parameterMap;

    /**
     * Constructor for Command.
     */
    protected Command() {}

    /**
     * Adds a parameter map to the command.
     * 
     * @param parameterMap Map of parameters to be added.
     */
    public void addParameterMap(Map<String, String> parameterMap) {
        this.parameterMap = parameterMap;
    }

    /**
     * Executes the command.
     * 
     * @param tasks TaskList to be used by the command.
     * @param ui Ui to be used by the command.
     * @param storage Storage to be used by the command.
     * @throws DukeException If an error occurs during execution of command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
