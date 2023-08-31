package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class MarkCommand extends Command {
    int index;
    public MarkCommand(int index) {
        this.index = index;
    }
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.getTask(index).markAsDone();
            storage.writeData(tasks.toWriteString());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Wow, that's a nonexistent task. Check your tasks again with 'list'.");
        }
    }
}
