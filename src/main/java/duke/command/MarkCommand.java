package duke.command;

import duke.error.DukeException;
import duke.lib.Storage;
import duke.lib.UI;
import duke.task.TaskList;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a MarkCommand object with the specified task index.
     *
     * @param index The index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command, marking the specified task as done and updating the task list.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler to save the updated task list.
     * @throws DukeException If there's an error marking the task or saving the task list.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ui.showMessage(String.format("Nice! I've marked this task as done:\n\t%s\n", tasks.markTask(index)));
        storage.save(tasks);
    }
}
