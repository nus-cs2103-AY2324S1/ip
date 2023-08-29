package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of tasks.
 */
public class TaskList {

    private List<Task> list;

    /**
     * Constructs an empty list of tasks.
     * @param capacity Maximum tasks allowed in the list.
     */
    public TaskList(Integer capacity) {
        this.list = new ArrayList<>(capacity);
    }

    /**
     * Gets the list of tasks.
     * @return The list of tasks.
     */
    public List<Task> getList() {
        return this.list;
    }

    /**
     * Prints the list of tasks nicely.
     */
    public void showList() {
        int i;
        for (i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.printf("%d.%s\n", i + 1, task);
        }
    }

    /**
     * Gets the specific task.
     * @param taskNumber The index of the task.
     * @return The task.
     * @throws DukeException Task number is invalid.
     */
    public Task getTask(Integer taskNumber) throws DukeException {
        if (taskNumber > list.size() || taskNumber < 1) {
            throw new DukeException("Invalid task number");
        } else {
            return list.get(taskNumber - 1);
        }
    }

    /**
     * Removes the specific task.
     * @param taskNumber The index of the task.
     * @throws DukeException Task number is invalid.
     */
    public void removeTask(Integer taskNumber) throws DukeException {
        if (taskNumber > list.size() || taskNumber < 1) {
            throw new DukeException("Invalid task number");
        } else {
            Task task = list.remove(taskNumber - 1);
            System.out.printf("Noted. I've removed this task:\n" +
                    "%s\n" + "Now you have %d tasks in the list.\n" +
                    "----------\n", task, list.size());
        }
    }

    /**
     * Adds a task.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        list.add(task);
        System.out.printf("Got it. I've added this task:\n" +
                "%s\n" + "Now you have %d tasks in the list.\n" +
                "----------\n", task, list.size());
    }
}
