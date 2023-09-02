package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * Command to print list of tasks.
 */
public class ListCommand implements Command {
    /**
     * Prints task list.
     *
     * @param taskList TaskList.
     * @param ui UI.
     * @param storage Storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.print();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
