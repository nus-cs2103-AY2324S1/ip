package duke.tasklist;

import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

import duke.exception.DukeException;

import java.util.ArrayList;

/**
 * Represents the list of tasks in the chatbot.
 */
public class TaskList {

    ArrayList<Task> taskList;

    /**
     * Constructor for the taskList object.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Marks the specified task as being done.
     *
     * @param number Number of the task, starting from index 1.
     * @return The task being marked as done.
     */
    public Task mark(Integer number) {
        if (number > taskList.size() || number < 0) {
            throw new DukeException("Wrong Param");
        }

        Task task = taskList.get(number-1);

        task.markAsDone();

        return task;
    }

    /**
     * Unmarks the specified task as being done.
     *
     * @param number Number of the task, starting from index 1.
     * @return The task being unmarked.
     */
    public Task unmark(Integer number) {
        if (number > taskList.size() || number < 0) {
            throw new DukeException("Wrong Param");
        }

        Task task = taskList.get(number-1);

        task.unmarkAsDone();

        return task;
    }

    /**
     * Deleted the specified task.
     *
     * @param number Number of the task, starting from index 1.
     * @return The task being deleted.
     */
    public Task delete(Integer number) {
        if (number > taskList.size() || number < 0) {
            throw new DukeException("Wrong Param");
        }

        Task task = taskList.remove(number-1);

        return task;
    }

    /**
     * Adds a specific task to the tasklist.
     *
     * @param task Task object to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Prints out all the task in the tasklist.
     */
    public void list() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i+1 + ": " + taskList.get(i));
        }
    }

    /**
     * Returns the task list as an ArrayList object to be stored.
     * @return task list.
     */
    public ArrayList<Task> get() {
        return this.taskList;
    }

    /**
     * Allows the task list to be set after reading the saved file.
     * @param taskList Stored tasklist.
     */
    public void set(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
}
