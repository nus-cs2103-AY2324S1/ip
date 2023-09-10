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
        assert (list != null) : "List of tasks must be instantiated.";
        return this.list;
    }

    /**
     * Checks if list of tasks is empty.
     * @return True if list is empty.
     */
    public boolean isEmpty() {
        assert (list != null) : "List of tasks must be instantiated.";
        return (list.size() == 0);
    }

    /**
     * Prints the list of tasks nicely.
     */
    public String showList() {
        int i;
        String listDisplay = "Here are your tasks: \n";
        assert (list != null) : "List of tasks must be instantiated.";
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
        assert (list != null) : "List of tasks must be instantiated.";

        boolean taskExceedListSize = taskNumber > list.size();
        boolean taskSmallerThanOne = taskNumber < 1;
        boolean invalidTaskNumber = taskExceedListSize || taskSmallerThanOne;

        if (invalidTaskNumber) {
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
        assert (list != null) : "List of tasks must be instantiated.";

        boolean taskExceedListSize = taskNumber > list.size();
        boolean taskSmallerThanOne = taskNumber < 1;
        boolean invalidTaskNumber = taskExceedListSize || taskSmallerThanOne;

        if (invalidTaskNumber) {
            throw new DukeException("Invalid task number");
        } else {
            Task task = list.remove(taskNumber - 1);
            return String.format("Noted. I've removed this task:\n"
                    + "%s\n" + "Now you have %d tasks in the list.\n",
                    task, list.size());
        }
    }

    /**
     * Adds a task.
     * @param task Task to be added.
     */
    public String addTask(Task task) {
        assert (list != null) : "List of tasks must be instantiated.";
        list.add(task);
        return String.format("Got it. I've added this task:\n"
                + "%s\n" + "Now you have %d tasks in the list.\n",
                task, list.size());
    }

    /**
     * Finds tasks based on keyword.
     * @param keyword Keyword to search for in tasks.
     */
    public String findTasks(String keyword) {
        assert (list != null) : "List of tasks must be instantiated.";
        TaskList tasks = new TaskList(this.capacity);
        for (Task task: list) {
            if (task.match(keyword)) {
                tasks.list.add(task);
            }
        }
        if (tasks.isEmpty()) {
            return ("There are no matching tasks in your list.");
        } else {
            return String.format("Here are the matching tasks in your list: \n"
                    + tasks.showList());
        }
    }
}
