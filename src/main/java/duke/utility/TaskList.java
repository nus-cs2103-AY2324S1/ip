package duke.utility;

import duke.exception.InvalidTaskException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * TaskList class encapsulates an ArrayList of all the tasks
 * users have added into their Bobi task list.
 *
 * @author ruo-x
 */
public class TaskList {
    /** ArrayList of tasks in the task list */
    private ArrayList<Task> array;

    /**
     * Constructor of a TaskList object.
     */
    public TaskList() {
        this.array = new ArrayList<>();
    }

    /**
     * Adds a new task into the task list.
     *
     * @param newTask New task to be added.
     */
    public void addTask(Task newTask) {
        this.array.add(newTask);
    }

    /**
     * Retrieves the task at the specified task number.
     *
     * @param taskNumber Specified task number.
     * @return Task retrieved of that specified task number.
     * @throws InvalidTaskException if task number is not within the length of task list.
     */
    public Task getTask(int taskNumber) throws InvalidTaskException {
        if (taskNumber > this.array.size() || taskNumber == 0) {
            throw new InvalidTaskException();
        }
        return this.array.get(taskNumber - 1);
    }

    /**
     * Returns the length of task list.
     *
     * @return Length of task list.
     */
    public int getLength() {
        return this.array.size();
    }

    /**
     * Remove a task from the task list.
     *
     * @param task Task to remove.
     */
    public void deleteTask(Task task) {
        for (int i = 0; i < 100; i++) {
            if (this.array.get(i).equals(task)) {
                this.array.remove(i);
                break;
            }
        }
    }

    /**
     * Returns the task list in a String format.
     *
     * @return Task list in a String format.
     */
    @Override
    public String toString() {
        // display in numerical pointers
        String list = "";
        for (int i = 0; i < this.array.size(); i++) {
            Task task = this.array.get(i);
            if (task == null) {
                break;
            } else {
                list += (i + 1) + "." + task + "\n";
            }
        }
        return list;
    }
}
