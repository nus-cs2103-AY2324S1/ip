package bareum.commands;

import bareum.Storage;
import bareum.TaskList;
import bareum.Ui;

/**
 * This class implements the command for exiting the program.
 */

public class ByeCommand extends Command {
    public ByeCommand() {

    }

    /**
     * Exit the program.
     * @param ui Lets the user know if the exit was successful.
     * @param storage Saves all the tasks in the task list to the hard disk.
     * @param taskList Task list to save the tasks from.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        storage.saveAllTasks(taskList);
        ui.showGoodbyeMessage();
    }
}
