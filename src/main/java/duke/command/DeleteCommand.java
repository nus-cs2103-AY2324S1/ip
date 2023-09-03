package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.task.Task;


/**
 * The DeleteCommand represents a command
 * to delete a task from the TaskList.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Sets the isExit to false and adds the index to be deleted
     * @param index the index to be deleted.
     */
    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        Task t = tasks.delete(index);
        storage.rewrite(tasks);
        return(ui.showDeleteMessage(t, tasks.getSize()));
    }
}
