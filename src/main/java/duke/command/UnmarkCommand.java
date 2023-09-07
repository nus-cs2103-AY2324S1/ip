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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String returnMessage = tasks.getTask(index).markAsUndone();
            storage.writeData(tasks.toWriteString());
            return returnMessage;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Wow, that's a nonexistent task. Check your tasks again with 'list'.");
        }
    }
}
