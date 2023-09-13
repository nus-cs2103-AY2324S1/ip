package duke.command;
import java.io.IOException;

import duke.TaskList;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;



/**
 * Represents an add command.
 * The add command is used to add tasks to the task list.
 */
public class AddCommand extends Command {

    /**
     * The task to be added.
     */
    private final Task task;

    /**
     * Creates a new AddCommand with the specified task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {

        this.task = task;
    }

    /**
     * Indicates whether this command will exit the application.
     *
     * @return false since this command does not exit the application.
     */
    @Override
    public boolean isExit() {

        return false;
    }

    /**
     * Executes the add command, adding the task to the task list,
     * saving the task list, and notifying the user.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface.
     * @param storage The storage to save tasks.
     * @throws IOException If there's an error saving the tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTask(task);
        storage.save(tasks);
        return ui.showAddedTask(task);
    }
}
