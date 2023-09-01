package ben;

/**
 * Represents a list command.
 */
public class ListCommand extends Command{
    /**
     * Checks whether command causes the chatbot to exit.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the list command.
     *
     * @param tasks The taskList
     * @param ui The UI handling user interaction
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.displayList(tasks);
    }
}
