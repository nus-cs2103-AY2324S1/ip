package harvard;
/**
 * Represents a command.
 */
public abstract class Command {
    /**
     * Executes the command.
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage.
     * @throws DukeException If there is an error executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns true if the command is an exit command.
     * @return True if the command is an exit command.
     */
    public abstract boolean isExit();
}




