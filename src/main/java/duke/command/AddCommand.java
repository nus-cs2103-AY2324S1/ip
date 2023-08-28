package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.task.Task;

import java.io.IOException;

/**
 * The AddCommand represents a command
 * to add a task to the TaskList.
 */

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        super(false);
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        tasks.add(this.task);
        storage.append(this.task);
        ui.showAddMessage(task, tasks.getSize());
    }
}
