package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.task.Task;

/**
 * The AddCommand represents a command
 * to add a task to the TaskList.
 */

public class AddCommand extends Command {
    private Task task;

    /**
     * Sets the isExit to false and adds the task.
     *
     * @param task the task to be added.
     */
    public AddCommand(Task task) {
        super(false);
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        tasks.add(this.task);
        storage.append(this.task);
        return (ui.showAddMessage(task, tasks.getSize()));
    }
}
