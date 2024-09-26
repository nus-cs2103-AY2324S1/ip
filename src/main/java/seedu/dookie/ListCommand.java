package seedu.dookie;

/**
 * Encapsulates the List Command.
 */
public class ListCommand extends Command {
    private Ui ui;
    private TaskList tasks;

    /**
     * Creates a new ListCommand instance.
     *
     * @param tasks The accumulated tasks.
     * @param ui The Ui being used.
     */
    public ListCommand(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Returns a string containing the current tasks.
     *
     * @return A string containing the current tasks.
     */
    public String execute() {
        return ui.getListItemsString(tasks);
    }
}
