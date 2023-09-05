package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;


/**
 * Command to List all the task in duke.TaskList.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage store) {
        return "Here are the tasks in your list:\n"
                + tasks.toString();
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
