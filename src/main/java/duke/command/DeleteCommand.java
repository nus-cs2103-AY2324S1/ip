package duke.command;

import duke.*;

public class DeleteCommand extends Command {
    Integer index;
    public DeleteCommand(Integer index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SaveToFileException, OutOfRangeException {
        ui.displayMessage(tasks.delete(index));
        storage.save(tasks);
    }
}
