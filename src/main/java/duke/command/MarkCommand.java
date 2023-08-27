package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.markTaskAsDone(index);
            ui.markTask(index, tasks);
            storage.update(tasks);
        } catch (DukeException e) {
            throw e;
        }
    }
}
