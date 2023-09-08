/**
 * Represents a command to display the command guide.
 */
public class HelpCommand extends Command {

    /**
     * Constructs a HelpCommand.
     */
    public HelpCommand() {
        super(null);
    }

    /**
     * Executes the HelpCommand, displaying the command guide to the user.
     *
     * @param tasks   The list of tasks (not used in this command).
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving tasks to a file (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showCommandGuide();
    }
}
