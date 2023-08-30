package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The DeleteCommand class represents a command to delete a task from the task list.
 *
 * @author selwyn
 */
public class DeleteCommand extends Command {
    /** The details of the task to be deleted. */
    private String taskToDeleteDetails;

    /**
     * Constructs a DeleteCommand object with the specified details of the task to be deleted.
     *
     * @param args The arguments specifying the task to be deleted.
     */
    public DeleteCommand(String args) {
        this.taskToDeleteDetails = args;
    }

    /**
     * Executes the DeleteCommand by removing a task from the task list and updating the storage.
     *
     * @param taskList The TaskList object containing the list of tasks.
     * @param ui The Ui object handling user interface interactions.
     * @param storage The Storage object handling storage-related operations.
     * @throws DukeException If there is an issue deleting the task or updating storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
                Task deletedTask = taskList.deleteTask(taskToDeleteDetails);
                int numOfTasksLeft = taskList.getNumTasks();
                ui.printDeletedTask(deletedTask, numOfTasksLeft);
                storage.saveTasks(taskList.getTaskList());
            } catch (DukeException e) {
                throw new DukeException(e.getMessage());
        }
    }
}