package duke.command;

import duke.OutOfRangeException;
import duke.SaveToFileException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.AlreadyMarkedException;

public class MarkCommand extends Command {
    private final Integer index;
    public MarkCommand(Integer index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws OutOfRangeException,
            AlreadyMarkedException, SaveToFileException {
        ui.displayMessage(tasks.mark(index));
        storage.save(tasks);
    }
}
