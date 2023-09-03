package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to show the task list.
 */
public class ListCommand extends Command {

    /**
     * {@inheritDoc}
     *
     * @param taskList List of Task objects.
     * @param ui UI that the user interact with.
     * @param storage Storage to handle data to and from an external file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showMessage(taskList.toString());
    }

    @Override
    public String executeGui(TaskList taskList, Ui ui, Storage storage) {
        return taskList.toString();
    }
}
