package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of tasks.
 */
public class TaskList {

    private List<Task> list;
    private Integer capacity;

    /**
     * Constructs an empty list of tasks.
     * @param capacity Maximum tasks allowed in the list.
     */
    public TaskList(Integer capacity) {
        this.list = new ArrayList<>(capacity);
        this.capacity = capacity;
    }

    /**
     * Gets the list of tasks.
     * @return The list of tasks.
     */
    public List<Task> getList() {
        return this.list;
    }
    public boolean isEmpty() {
        return (list.size() == 0);
    }

    /**
     * Prints the list of tasks nicely.
     */
    public String showList() {
        int i;
        String listDisplay = "Here are your tasks: \n";
        for (i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            listDisplay += String.format("%d.%s\n", i + 1, task);
        }
        return listDisplay;
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
    public String removeTask(Integer taskNumber) throws DukeException {
        if (taskNumber > list.size() || taskNumber < 1) {
            throw new DukeException("Invalid task number");
        } else {
            Task task = list.remove(taskNumber - 1);
            String output = String.format("Noted. I've removed this task:\n"
                    + "%s\n" + "Now you have %d tasks in the list.\n",
                    task, list.size());
            return output;
        }
    }

    /**
     * Adds a task.
     * @param task Task to be added.
     */
    public String addTask(Task task) {
        list.add(task);
        String output = String.format("Got it. I've added this task:\n"
                + "%s\n" + "Now you have %d tasks in the list.\n",
                task, list.size());
        return output;
    }

    /**
     * Finds tasks based on keyword.
     * @param keyword Keyword to search for in tasks.
     */
    public String findTasks(String keyword) {
        TaskList tasks = new TaskList(this.capacity);
        for (Task task: list) {
            if (task.match(keyword)) {
                tasks.list.add(task);
            }
        }
        if (tasks.isEmpty()) {
            return ("There are no matching tasks in your list.");
        } else {
            String output = String.format("Here are the matching tasks in your list: \n"
                    + tasks.showList());
            return output;
        }
    }
}
