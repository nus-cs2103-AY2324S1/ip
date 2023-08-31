package duke.commands;

import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

public abstract class Command {
/**
 * Represents a command that can be executed on the task list.
 */

    protected String fullCommand;
    protected boolean isExit;

    /**
     * Constructs a command with the given full command string.
     *
     * @param fullCommand The full command string.
     */
    public Command(String fullCommand) {
        this.fullCommand = fullCommand;
        isExit = false;
    }

    /**
     * Constructs a command with no command string.
     */
    public Command() {
        isExit = false;
    }

    /**
     * Executes the command using the provided task list, user interface, and storage.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for data persistence.
     * @throws Exception Any exceptions that may occur during command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    /**
     * Checks if the command is an exit command to determine whether bot should end.
     *
     * @return True if the command is an exit command, otherwise false.
     */
    public boolean isExit() {
        return isExit;
    }
}
