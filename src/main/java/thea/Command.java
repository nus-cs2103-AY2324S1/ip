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
     * @param thea reference to the chatbot containing relevant data.
     */
    public abstract String execute(Thea thea);
}
