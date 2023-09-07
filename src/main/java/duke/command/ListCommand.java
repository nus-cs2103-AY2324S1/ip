package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * The ListCommand class.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printTaskList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
