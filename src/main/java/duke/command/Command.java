package duke.command;
import java.io.IOException;

import duke.DukeException;
import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;




/**
 * Represents an abstract command to be executed in the Duke program.
 * This class provides a template for specific commands like adding or deleting tasks.
 */
public abstract class Command {

    /**
     * Indicates whether the command will cause the program to exit.
     */
    private boolean isExit = false;

    /**
     * Executes the specific command.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface handler.
     * @param storage The storage handler to load or save tasks.
     * @throws IOException   If there's an error accessing or modifying the storage.
     * @throws DukeException If there are issues executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException;

    /**
     * Determines if Duke should continue listening for commands or terminate.
     *
     * @return true if Duke should exit, false otherwise.
     */
    public boolean isExit() {

        return this.isExit;
    }
}
