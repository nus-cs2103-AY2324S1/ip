package command;

import duke.Storage;
import duke.Ui;
import task.TaskList;

/**
 * Prints out a help message for users
 */
public class HelpCommand extends Command {

    /**
     * Displays a help message which shows commands and instructions to user
     *
     * @param task TaskList which contains an ArrayList of tasks
     * @param storage File path where the tasks are stored
     * @return help message with commands and instructions
     */
    @Override
    public String execute(TaskList task, Storage storage) {
        return Ui.helpMessage();
    }
}
