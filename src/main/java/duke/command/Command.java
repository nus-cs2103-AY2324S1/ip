package duke.command;

import duke.main.*;
import duke.exception.*;
import duke.task.*;
import java.io.IOException;

/**
 * The Command class is the base class for all command type classes.
 */
public class Command {
    public Task task;

    /**
     * Constructs a Command object with the specified task.
     *
     * @param task The task associated with the command.
     */
    public Command(Task task) {
        this.task = task;
    }

    /**
     * Executes the command depending on the specific command type.
     *
     * @param tasks The task list to interact with.
     * @param ui The user interface for displaying messages.
     * @param storage The storage object for saving or loading tasks.
     * @throws IOException If there's an error while updating storage.
     * @throws InvalidTaskNumberException If an invalid task number is encountered during execution.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, InvalidTaskNumberException {
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return true if the command represents an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
