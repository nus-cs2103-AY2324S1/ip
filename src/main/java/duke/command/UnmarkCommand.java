package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;

public class UnmarkCommand extends Command {
    private Integer index;
    public UnmarkCommand(Integer index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.displayMessage(tasks.unmark(index));
        storage.save(tasks);
    }
}
