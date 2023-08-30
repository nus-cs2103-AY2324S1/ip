/**
 * The AddCommand class represents a command to add a new task to the task list.
 *
 * @author selwyn
 */
public class AddCommand extends Command {
    /** The type of task to be added. */
    private TaskType taskType;

    /** The details for creating the task. */
    private String addCommandDetails;

    /**
     * Constructs an AddCommand object with the specified task type and details.
     *
     * @param taskType The type of task to be added.
     * @param args The arguments for creating the task.
     */
    public AddCommand(TaskType taskType, String args) {
        this.taskType = taskType;
        this.addCommandDetails = args;
    }

    /**
     * Executes the AddCommand by adding a new task to the task list and updating the storage.
     *
     * @param taskList The TaskList object containing the list of tasks.
     * @param ui The Ui object handling user interface interactions.
     * @param storage The Storage object handling storage-related operations.
     * @throws DukeException If there is an issue adding the task or updating storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Task addedTask = taskList.addTask(this.taskType, this.addCommandDetails);
            ui.printAddedTask(addedTask, taskList.getNumTasks());
            storage.saveTasks(taskList.getTaskList());
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }
}