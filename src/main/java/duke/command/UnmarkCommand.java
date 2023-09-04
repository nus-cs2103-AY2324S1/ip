package duke.command;

import duke.Command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Task;

/**
 * Represents a command to unmark a task as not done in the task list.
 * The UnmarkCommand is responsible for marking a specific task as not done (incomplete).
 */
public class UnmarkCommand extends Command {

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param taskIndex The index of the task to be unmarked (marked as not done).
     */
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the UnmarkCommand.
     * This method marks the task at the specified index as not done (incomplete) and updates the task list.
     * If the index is invalid, an error message is displayed.
     *
     * @param taskList The TaskList object that stores the list of tasks.
     * @param ui       The Ui object responsible for user interface interactions.
     * @param storage  The Storage object responsible for reading and writing data to a file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task tasktoUnmark = taskList.getTask(taskIndex);
            tasktoUnmark.markAsNotDone();
            ui.displayUnmarked(tasktoUnmark);
            storage.saveTasks(taskList.getAllTasks()); // Save the updated task list
        } catch (IndexOutOfBoundsException e) {
            ui.showErrorMessage("Invalid task index.");
        }
    }
}