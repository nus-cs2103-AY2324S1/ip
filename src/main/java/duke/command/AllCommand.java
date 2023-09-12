package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This command functions to list all available commands.
 */
public class AllCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui) {
        return ui.all();
    }
}
