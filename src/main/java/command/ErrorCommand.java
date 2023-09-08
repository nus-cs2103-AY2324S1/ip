package command;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Represents a command to display an error message in the UI.
 * This class extends the Command class and contains method to execute the error command
 * and to check whether it is an exit command.
 */
public class ErrorCommand extends Command {
    // The error message to be displayed in the UI
    String errorMessage;

    /**
     * Creates a new ErrorCommand with the specified error message to be displayed in the UI.
     *
     * @param errorMessage The error message to be displayed in the UI.
     */
    public ErrorCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Executes the error command by displaying the error message in the UI.
     *
     * @param tasks The list of tasks, which is not utilized in this method but is here to override the abstract method in the parent class.
     * @param ui The UI where the error message will be displayed.
     * @param storage The storage of tasks, which is not utilized in this method but is here to override the abstract method in the parent class.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(this.errorMessage);
    }

    /**
     * Specifies that this command is not an exit command.
     *
     * @return false, as this command does not cause the program to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
