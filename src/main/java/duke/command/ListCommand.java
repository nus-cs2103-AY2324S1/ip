package duke.command;

import duke.main.*;
import duke.exception.*;
import duke.task.*;
import java.io.IOException;

/**
 * The ListCommand represents the command to list the tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Constructs a ListCommand object to display all tasks in the task list.
     *
     * @param task The task associated with the command (not used).
     */
    public ListCommand(Task task) {
        super(task);
    }

    /**
     * Executes the command to display the list of tasks.
     *
     * @param tasks The task list to interact with.
     * @param ui The user interface for displaying messages.
     * @param storage The storage object for saving or loading tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks.getTasks());
    }
}
