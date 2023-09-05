package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The DeleteCommand class represents a command for deleting tasks from the task list.
 *
 * @author selwyn
 */
public class DeleteCommand extends Command {
    private String taskToDeleteDetails;

    /**
     * Constructs a DeleteCommand with the specified details of the task to be deleted.
     *
     * @param args The details of the task to be deleted.
     */
    public DeleteCommand(String args) {
        this.taskToDeleteDetails = args;
    }

    /**
     * Executes the DeleteCommand by deleting a task from the task list and saving it to storage.
     *
     * @param taskList The task list from which the task will be deleted.
     * @param storage  The storage object used for saving tasks.
     * @return A message indicating the successful deletion of the task.
     * @throws DukeException If there is an error deleting the task.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        int numOfTasksLeft;
        Task deletedTask;
        try {
            deletedTask = taskList.deleteTask(taskToDeleteDetails);
            numOfTasksLeft = taskList.getNumTasks();
            storage.saveTasks(taskList.getTaskList());
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }

        return Ui.printDeletedTask(deletedTask, numOfTasksLeft);
    }
}
