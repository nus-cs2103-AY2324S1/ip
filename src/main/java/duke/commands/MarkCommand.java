package duke.commands;

import duke.exceptions.CommandDetailException;
import duke.exceptions.StorageException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws StorageException, CommandDetailException {
        ui.showMark(tasks, index);
        tasks.markTask(index);
        storage.save(tasks);
    }


    @Override
    public boolean isExit() {
        return false;
    }
}
