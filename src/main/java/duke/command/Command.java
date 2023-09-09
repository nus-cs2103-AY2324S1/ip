package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

/**
 * Represents an abstract command that can be executed by Duke.
 * All specific command types (e.g., AddTaskCommand, DeleteTaskCommand)
 * should inherit from this class and implement the execute method.
 */
public abstract class Command {
    /**
     * Executes the specific command with respect to the provided task list,
     * user interface, and storage handler.
     *
     * @param tasks   The list of tasks to operate on.
     * @param ui      The UI instance to communicate with the user.
     * @param storage The storage instance to read/write to the data file.
     * @throws DukeException If there's a problem specific to Duke's operation.
     * @throws IOException   If there's a problem accessing or modifying the data file.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;
}