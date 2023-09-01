package duke.command;

import duke.main.*;
import duke.exception.*;
import duke.task.*;

import java.io.IOException;

/**
 * The AddCommand class represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    /**
     * Constructs an AddCommand with the specified task to be added.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        super(task);
    }

    /**
     * Executes the AddCommand to add the task to the task list, update storage, and display a success message.
     *
     * @param tasks The task list to which the task should be added.
     * @param ui The user interface for displaying messages.
     * @param storage The storage object for saving tasks to a file.
     * @throws IOException If there's an error while updating the storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.add(task);
        storage.append(task);
        ui.showAddSuccess(task.toString(), tasks.getTasks().size());
    }
}
