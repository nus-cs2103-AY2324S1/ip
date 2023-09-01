package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The Command class is an abstract base class for all commands that can be executed in the Duke application.
 * Each concrete subclass of Command represents a specific user command.
 */
public abstract class Command {
    protected String fullCommand;

    /**
     * Constructs a new Command object with the specified full command string.
     *
     * @param fullCommand The full command string as entered by the user.
     */
    public Command(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return true if the command is an exit command; false otherwise.
     */
    public abstract boolean isExit();

    /**
     * Executes the command, performing the necessary actions on the task list, user interface, and storage.
     *
     * @param tasks   The task list to be operated on by the command.
     * @param ui      The user interface to display messages or interact with the user.
     * @param storage The storage object to read from or write to a data file.
     */
    public abstract void execute(TaskList tasks , Ui ui, Storage storage);
}
