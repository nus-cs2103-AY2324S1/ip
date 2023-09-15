package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.task.Task;

/**
 * The MarkCommand is a Command that
 * marks a task as completed.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Sets the isExit to false and adds
     * the index to mark
     *
     * @param index the index to mark.
     */
    public MarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        Task task = tasks.mark(index);
        storage.rewrite(tasks);
        return (ui.showMarkMessage(task));
    }
}
