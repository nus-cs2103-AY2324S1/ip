package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;

/**
 * The AddCommand class represents a command for adding tasks to the task list.
 *
 * @author selwyn
 */
public class AddCommand extends Command {
    private TaskType taskType;

    private String addCommandDetails;

    /**
     * Constructs an AddCommand with the specified task type and command details.
     *
     * @param taskType          The type of task to be added.
     * @param addCommandDetails The details of the task to be added.
     */
    public AddCommand(TaskType taskType, String addCommandDetails) {
        this.taskType = taskType;
        this.addCommandDetails = addCommandDetails;
    }

    /**
     * Executes the AddCommand by adding a task to the task list and saving it to storage.
     *
     * @param taskList The task list to which the task will be added.
     * @param storage  The storage object used for saving tasks.
     * @return A message indicating the successful addition of the task.
     * @throws DukeException If there is an error adding the task.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        Task addedTask;
        try {
            addedTask = taskList.addTask(this.taskType, this.addCommandDetails);
            storage.saveTasks(taskList.getTaskList());
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }

        return Ui.printAddedTask(addedTask, taskList.getNumTasks());
    }
}
