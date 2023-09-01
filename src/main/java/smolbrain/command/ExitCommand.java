package smolbrain.command;

import smolbrain.Storage;
import smolbrain.Ui;
import smolbrain.task.TaskList;

/**
 * Handles exiting chatbot.
 */
public class ExitCommand implements Command {

    private boolean loading;

    /**
     * Executes this command.
     *
     * @param tasks List of tasks of chatbot.
     * @param ui Ui manager of chatbot.
     * @param storage Storage manager of chatbot.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Indicates if this command causes chatbot to exit.
     *
     * @return Boolean value if this command exits the chatbot.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Sets the flag that chatbot is loading to true.
     */
    @Override
    public void setLoading() {
        this.loading = true;
    }

}
