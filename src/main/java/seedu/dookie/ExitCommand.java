package seedu.dookie;

/**
 * Encapsultes the Exit Command.
 */
public class ExitCommand extends Command {
    private Ui ui;

    /**
     * Creates a new ExitCommand instance.
     *
     * @param ui The Ui being used.
     */
    public ExitCommand(Ui ui) {
        this.ui = ui;
    }

    /**
     * Returns a string containing the exit message.
     *
     * @return A string containing the exit message.
     */
    public String execute() {
        return ui.getExitMessage();
    }
}
