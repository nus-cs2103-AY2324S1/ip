package duke.command;

import duke.error.DukeException;
import duke.lib.Storage;
import duke.lib.UI;
import duke.task.TaskList;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ui.showMessage(String.format("OK, I've marked this task as not done yet:\n\t%s\n",
                tasks.unmarkTask(this.index)));
        storage.save(tasks);
    }
}
