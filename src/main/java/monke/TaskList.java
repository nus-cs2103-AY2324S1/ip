package monke;

import monke.tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class represents a list of tasks in the Monke application.
 */
public class TaskList {
    /** The list to store the tasks */
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList by its number.
     *
     * @param taskNum The number of the task to be deleted.
     * @throws MonkeException If the number provided is not between 1 and size of list.
     */
    public void delete(String taskNum) throws MonkeException {
        try {
            int n = Integer.parseInt(taskNum);
            if (n > this.tasks.size() || n <= 0) {
                throw new MonkeException("OOGA BOOGA!! Your number is out of range. :(");
            }
            this.tasks.remove(n - 1);
        } catch (NumberFormatException e) {
            throw new MonkeException("OOGA BOOGA!! You must provide a number from the list. :(");
        }
    }

    /**
     * Retrieves a task from the TaskList by its number.
     *
     * @param taskNum The number of the task to be retrieved.
     * @return The task with the specified number.
     * @throws MonkeException If the number provided is not between 1 and size of list.
     */
    public Task getTask(String taskNum) throws MonkeException {
        try {
            int n = Integer.parseInt(taskNum);
            if (n > this.tasks.size() || n <= 0) {
                throw new MonkeException("OOGA BOOGA!! Your number is out of range. :(");
            }
            return tasks.get(n - 1);
        } catch (NumberFormatException e) {
            throw new MonkeException("OOGA BOOGA!! You must provide a number from the list. :(");
        }
    }

    /**
     * Converts the TaskList to a list of tasks.
     *
     * @return A List object containing the tasks in the TaskList.
     */
    public List<Task> toList() {
        return this.tasks;
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int size() {
        return this.tasks.size();
    }
}
