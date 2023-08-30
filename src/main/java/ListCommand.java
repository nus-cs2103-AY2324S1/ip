/**
 * Represents a Command class that is responsible for listing the tasks in the list of tasks.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
 */
public class ListCommand extends Command{
    /**
     * Constructor for the ListCommand class for users to list all the tasks.
     */
    public ListCommand() {
        super();
    }

    /**
     * Lists all the tasks.
     *
     * @param tasks TaskList class storing an ArrayList of Task objects.
     * @param ui Ui class to handle user interactions.
     * @param storage Storage class to read and store tasks by the user.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showListTasks(tasks, tasks.getNumOfTasks());
    }
}
