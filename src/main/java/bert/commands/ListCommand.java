package bert.commands;

import bert.storage.Storage;
import bert.tasks.TaskList;
import bert.ui.Ui;

/**
 * Represents a command that prints out the list of tasks in a task list.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    private static final String MESSAGE = "Here are the tasks in your list:\n%1$s";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showResult(String.format(MESSAGE, tasks));
    }
}
