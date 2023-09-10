package miles.command;

import miles.TaskList;
import miles.Storage;
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
