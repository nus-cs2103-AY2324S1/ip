package sally;

/**
 * Represents a command to be executed by the program.
 */
public interface Command {
    /**
     * Executes the command on the given TaskList and Storage.
     *
     * @param tasks The TaskList containing tasks.
     * @param storage The Storage for tasks.
     * @throws SallyException If there's an issue while executing the command.
     */
    public String execute(TaskList tasks, Storage storage) throws SallyException;
}
