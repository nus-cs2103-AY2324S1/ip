package gudetama.commands;

import gudetama.storage.Storage;
import gudetama.tasks.TaskList;
import gudetama.ui.Ui;

/**
 * Represents a command for handling error messages
 */
public class ErrorCommand extends Command{

    private String message;

    public ErrorCommand(String message){
        this.message = message;
    }

    /**
     * Returns a boolean value to indicate whether to exit the program
     * @return A boolean value
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command to show error message when input is invalid
     * @param tasksList Task list which contains the tasks
     * @param ui        A UI instance that displays a message to the user
     * @param storage   Storage instance that represents the storage of the file
     */
    @Override
    public String execute(TaskList tasksList, Ui ui, Storage storage) {
        return ui.showErrorMessage(message);
    }
}