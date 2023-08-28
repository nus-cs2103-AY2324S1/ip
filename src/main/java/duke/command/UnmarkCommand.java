package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;

import java.io.IOException;

/**
 * The UnmarkCommand represents a Command that
 * unmarks a specific task.
 */
public class UnmarkCommand extends Command {
    int index;

    public UnmarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        tasks.unmark(index);
        storage.rewrite(tasks);
        ui.showUnmarkMessage();
    }
}
