package fishron;

/**
 * Represents a command that can be executed.
 */
public abstract class Command {

    /**
     * A flag indicating whether this command is an exit command.
     */
    private boolean isExit;

    /**
     * Constructs a command.
     *
     * @param isExit A flag indicating whether this command is an exit command.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param taskList The task list to perform the command on.
     * @param ui       The user interface to interact with the user.
     * @param storage  The storage to read from or write to.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage)  {
        // This method is overridden by concrete command classes.
    }

    /**
     * Checks if this command is an exit command.
     *
     * @return True if this command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
