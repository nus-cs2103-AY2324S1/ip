package duke.commands;

import duke.exceptions.StorageException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command {

    private final Task task;


    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StorageException {
        tasks.add(task);
        ui.showAddTask(tasks, task);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
