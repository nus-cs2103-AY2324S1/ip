package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to list tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand object.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Executes the list command, displaying the list of tasks.
     *
     * @param taskList The task list to operate on.
     * @param storage The storage handler (not used in this command).
     * @param ui The user interface for displaying the list of tasks.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.showList(taskList);
    }

}
