package duke.commands;

import duke.io.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * An ExitCommand class that encapsulates the event of terminating the Duke program.
 */

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String message = "Bye. Hope to see you again soon!";
        ui.appendResponse(message);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
