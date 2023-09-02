package haste.commands;

import haste.data.TaskList;
import haste.ui.Ui;

/**
 * Represents a command that prints all the tasks in the TaskList.
 */
public class ListCommand extends Command {

    /**
     * Executes the command.
     *
     * @param tasks TaskList containing the tasks.
     * @param ui Ui that handles interactions.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.printList(tasks);
    }
}
