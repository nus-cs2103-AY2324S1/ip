package command;

import duke.Storage;
import duke.Ui;

import task.TaskList;

/**
 * Prints out the current todo list
 */
public class ListCommand extends Command {

    /**
     * Prints the current TaskList
     *
     * @param tasks TaskList which contains an ArrayList of tasks
     * @param ui Text Ui that the user interacts with
     * @param storage File path where the tasks are stored
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printTaskList();
    }
}
