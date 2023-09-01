package commands;

import storage.Storage;

import tasks.Task;
import tasks.TaskList;

import ui.Ui;

public abstract class Command {
    /**
     * A flag indicating whether this command should exit the application.
     */
    private boolean isExit;

    /**
     * The type of command.
     */
    private CommandType type;

    /**
     * Initializes a new Command instance with the specified exit flag and command type.
     *
     * @param isExit The flag indicating whether this command should exit the application.
     * @param type The type of command.
     */
    public Command(boolean isExit, CommandType type) {
        this.isExit = isExit;
        this.type = type;
    }
    
    /**
     * Executes the command, performing its intended action on the provided task list,
     * user interface, and storage.
     *
     * @param list The task list to perform the command action on.
     * @param ui The user interface for displaying feedback to the user.
     * @param storage The storage for saving and loading tasks (if applicable).
     * @throws CommandExecutionException If an error occurs during command execution.
     */
    public abstract void execute(TaskList list, Ui ui, Storage<Task> storage) throws CommandExecutionException;
    
    /**
     * Checks whether this command should exit the application.
     *
     * @return True if this command should exit, else false.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Gets the type of this command.
     *
     * @return The type of command.
     */
    public CommandType getCommandType() {
        return this.type;
    }
}
