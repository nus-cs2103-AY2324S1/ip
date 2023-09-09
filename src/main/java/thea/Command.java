package thea;

/**
 * Represents an executable user command.
 * Subclasses of this abstract class are specific
 * commands with specified execute method behaviours.
 */
public abstract class Command {
    private final boolean isExit;

    /**
     * Constructs a new Command object.
     * If the Command type is ExitCommand, isExit is true.
     *
     * @param isExit boolean representing if command is ExitCommand.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean getIsExit() {
        return this.isExit;
    }

    /**
     * Execute the user command to the data.
     * To be implemented by subclasses.
     *
     * @param tasks list of current tasks.
     * @param ui Ui class that deals with user interaction.
     * @param storage Storage class that deals with saving data.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
