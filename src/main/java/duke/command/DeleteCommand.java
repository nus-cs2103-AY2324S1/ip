package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    Integer index;
    public DeleteCommand(Integer index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.displayMessage(tasks.delete(index));
        storage.save(tasks);
    }
}
