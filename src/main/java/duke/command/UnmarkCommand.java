package duke.command;

import duke.*;
import duke.task.AlreadyUnmarkedException;

public class UnmarkCommand extends Command {
    private final Integer index;
    public UnmarkCommand(Integer index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlreadyUnmarkedException,
            OutOfRangeException, SaveToFileException {
        ui.displayMessage(tasks.unmark(index));
        storage.save(tasks);
    }
}
