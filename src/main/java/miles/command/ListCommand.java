package miles.command;

import miles.Storage;
import miles.TaskList;
import miles.Ui;

/**
 * Represents a list command.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.displayList();
    }
}
