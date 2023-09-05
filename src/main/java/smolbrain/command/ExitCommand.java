package smolbrain.command;

import javafx.application.Platform;
import smolbrain.Storage;
import smolbrain.Ui;
import smolbrain.task.TaskList;

/**
 * Handles exiting chatbot.
 */
public class ExitCommand implements Command {

    private boolean loading;

    /**
     * Creates an exit command.
     */
    public ExitCommand() {
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
        ui.showMessage("Bye. Hope to see you again soon!");
        Platform.exit();
    }

    /**
     * Sets the flag that chatbot is loading to true.
     */
    @Override
    public void setLoading() {
        this.loading = true;
    }

}
