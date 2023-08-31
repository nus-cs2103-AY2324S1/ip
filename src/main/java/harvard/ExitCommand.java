package harvard;
/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {
    /**
     * Returns true if the command is an exit command.
     *
     * @return True if the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the command.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage.
     * @throws DukeException If there is an error executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.displayBye();
    }
}
