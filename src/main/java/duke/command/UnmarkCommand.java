package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * The UnmarkCommand represents a Command that
 * unmarks a specific task.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Sets the isExit to false and adds the index
     * to unmark.
     *
     * @param index the index to unmark.
     */
    public UnmarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.unmark(index);
        storage.rewrite(tasks);
        return (ui.showUnmarkMessage(task));
    }
}
