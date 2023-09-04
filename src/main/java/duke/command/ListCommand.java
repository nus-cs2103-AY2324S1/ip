package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This command functions to list all saved tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.list(tasks);
    }
}
