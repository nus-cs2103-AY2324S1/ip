package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.markTaskAsNotDone(index);
            ui.unmarkTask(index, tasks);
            storage.update(tasks);
        } catch (DukeException e) {
            throw e;
        }
    }
}
