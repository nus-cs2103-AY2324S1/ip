package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class UnmarkCommand extends Command {
    int index;
    public UnmarkCommand(int index) {
        this.index = index;
    }
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.getTask(index).markAsUndone();
            storage.writeData(tasks.toWriteString());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Wow, that's a nonexistent task. Check your tasks again with 'list'.");
        }
    }
}
