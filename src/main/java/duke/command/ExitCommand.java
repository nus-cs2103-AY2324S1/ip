package duke.command;

import duke.core.Duke;
import duke.core.Storage;
import duke.core.Ui;
import duke.task.TaskList;

/**
 * Command to exit the program.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.respond("Bye. Hope to see you again soon!");
        Duke.stop();
    }
}
