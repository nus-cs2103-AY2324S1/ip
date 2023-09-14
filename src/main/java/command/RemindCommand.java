package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to remind user of upcoming tasks.
 */
public class RemindCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage store) {
        return tasks.remindTasks();
    }

}
