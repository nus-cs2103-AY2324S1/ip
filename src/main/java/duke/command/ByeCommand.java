package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This command functions to terminate the program.
 */
public class ByeCommand extends Command {
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.bye();
    }
}
