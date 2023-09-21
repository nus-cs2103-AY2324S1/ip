package gudetama.commands;

import gudetama.storage.Storage;
import gudetama.tasks.TaskList;
import gudetama.ui.Ui;

/**
 * Represents a command for displaying a list of valid commands to the user
 */
public class HelpCommand extends Command{
    /**
     * Returns a boolean value to indicate whether to exit the program
     * @return A boolean value
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command to show a list of commands that the user can use
     * @param tasksList Task list which contains the tasks
     * @param ui        A UI instance that displays a message to the user
     * @param storage   Storage instance that represents the storage of the file
     */
    @Override
    public String execute(TaskList tasksList, Ui ui, Storage storage) {
        return ui.showHelp();
    }
}
