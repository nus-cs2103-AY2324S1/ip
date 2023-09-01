package smolbrain.command;

import smolbrain.Storage;
import smolbrain.Ui;
import smolbrain.task.TaskList;

/**
 * Handles finding task with keyword
 */
public class FindCommand implements Command {

    private String keyword;
    private boolean loading;

    /**
     * Creates the command.
     *
     * @param keyword Keyword to check.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes this command.
     *
     * @param tasks List of tasks of chatbot.
     * @param ui Ui manager of chatbot.
     * @param storage Storage manager of chatbot.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are the matching tasks in your list: ");
        tasks.findTasks(this.keyword, ui);
    }

    /**
     * Indicates if this command causes chatbot to exit.
     *
     * @return Boolean value if this command exits the chatbot.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Sets the flag that chatbot is loading to true.
     */
    @Override
    public void setLoading() {
        this.loading = true;
    }

}
