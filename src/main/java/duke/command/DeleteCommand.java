package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.task.Task;

import java.io.IOException;

/**
 * The DeleteCommand represents a command
 * to delete a task from the TaskList.
 */
public class DeleteCommand extends Command{
    int index;

    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        Task t = tasks.delete(index);
        storage.rewrite(tasks);
        ui.showDeleteMessage(t, tasks.getSize());
    }
}
