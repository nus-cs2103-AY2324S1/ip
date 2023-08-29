package sally;

/**
 * Represents a command to be executed by the program.
 */
public interface Command {
    /**
     * Executes the command on the given TaskList, Storage, and Ui.
     *
     * @param tasks The TaskList containing tasks.
     * @param storage The Storage for tasks.
     * @param ui The Ui for user interaction.
     * @throws SallyException If there's an issue while executing the command.
     */
    void execute(TaskList tasks, Storage storage, Ui ui) throws SallyException;
}
