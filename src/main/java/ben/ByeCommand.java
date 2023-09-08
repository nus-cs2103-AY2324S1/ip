package ben;

/**
 * Represents a bye command.
 */
public class ByeCommand extends Command{
    /**
     * Checks whether command causes the chatbot to exit.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the bye command.
     *
     * @param tasks The taskList
     * @param ui The UI handling user interaction
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        return ui.bye();
    }
}
