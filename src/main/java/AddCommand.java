/**
 * Represents a Command class that is responsible for adding a Task object to TaskList.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
 */
public class AddCommand extends Command{
    /** Task to be added to the list of Tasks. */
    private Task task;

    /**
     * Constructor for the AddCommand class to add a Task to the list of Tasks.
     *
     * @param task Task object to be added to the list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task to the list of tasks, prints add task message and
     * stores the updated list in the local file.
     *
     * @param tasks TaskList class storing an ArrayList of Task objects.
     * @param ui Ui class to handle user interactions.
     * @param storage Storage class to read and store tasks by the user.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.showAddedTask(task, tasks.getNumOfTasks());
        storage.saveFile(tasks.toStorageString());
    }
}
