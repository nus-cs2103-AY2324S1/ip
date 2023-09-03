package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A Command Class to list out the Task
 *
 * @author marioalvaro
 */
public class ListCommand extends Command{
    /**
     * Method to execute the list out.
     *
     * @param taskList The used TaskList
     * @param ui The ui object
     * @param storage The storage used
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printTaskList(taskList);
    }
}
