package duke.command;

import duke.OutOfRangeException;
import duke.SaveToFileException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    private final Integer index;
    public DeleteCommand(Integer index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SaveToFileException, OutOfRangeException {
        ui.displayMessage(tasks.delete(index));
        storage.save(tasks);
    }
}
