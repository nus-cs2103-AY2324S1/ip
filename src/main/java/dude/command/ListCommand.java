package dude.command;

import dude.Storage;
import dude.TaskList;
import dude.Ui;

/**
 * Represents a command that lists all the tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the command to list all tasks.
     *
     * @param taskList List of tasks.
     * @param storage Storage containing saved tasks, and saves and loads tasks.
     * @param ui User interface of Dude.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("Listing...");
        ui.showTaskList(taskList);
    }
}
