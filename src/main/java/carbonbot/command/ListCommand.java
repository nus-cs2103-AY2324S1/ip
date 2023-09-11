package carbonbot.command;

import carbonbot.Storage;
import carbonbot.TaskList;
import carbonbot.Ui;

/**
 * The command lists all the tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Constructs a ListCommand object.
     */
    public ListCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bufferMessage("Here are the tasks in your list:");
        ui.bufferMessage(tasks.getTasks());
    }
}
