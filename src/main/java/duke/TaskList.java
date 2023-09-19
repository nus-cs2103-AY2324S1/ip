package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;


/**
 * Collection of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty duke.TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list by index.
     *
     * @param index The index of the task to be removed.
     */
    public void remove(int index) {
        assert index >= 0 : "index should be >= 0";
        tasks.remove(index);
    }

    /**
     * Marks a task as done by index.
     *
     * @param index The index of the task to be marked as done.
     */
    public void mark(int index) {
        assert index >= 0 : "index should be >= 0";
        tasks.get(index).mark();
    }

    /**
     * Unmarks a task by index.
     *
     * @param index The index of the task to be unmarked.
     */
    public void unmark(int index) {
        assert index >= 0 : "index should be >= 0";
        tasks.get(index).unmark();
    }

    /**
     * Retrieves a task by index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Retrieves the size of the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Provides a string representation of the task list.
     *
     * @return A string representation of the task list.
     */
    @Override
    public String toString() {
        if (tasks.size() == 0) {
            return "No tasks right now..\nHave a nice rest!";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append(i + 1).append(".").append(tasks.get(i).toString());
            if (i != tasks.size() - 1) {
                stringBuilder.append("\n");
            }
        }
        if (tasks.size() > 5) {
            stringBuilder.append("\n\nWah a lot of tasks to do!");
        }
        return stringBuilder.toString();
    }

    /**
     * Returns the task on a specific date.
     *
     * @param date of the task to find.
     * @return all task on the date.
     * @throws DukeException
     */
    public String getByDate(String date) throws DukeException {
        LocalDate targetDate;
        try {
            targetDate = LocalDate.parse(date);
        } catch (DateTimeException e) {
            throw new DukeException("Wrong date format. Please Use format YYYY-MM-DD for searching by date");
        }

        if (tasks.size() == 0) {
            return "You don't have any tasks at all";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here is want I found:\n\n");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).onDate(targetDate)) {
                stringBuilder.append(i + 1).append(".").append(tasks.get(i).toString());
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Find the tasks with a keyword.
     *
     * @param keyword to search.
     * @return The list format of the task that contains the keyword.
     */
    public String find(String keyword) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).contains(keyword)) {
                stringBuilder.append(i + 1).append(".").append(tasks.get(i).toString());
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Find the nearest deadline or event.
     *
     * @return list of the nearest task.
     * @throws DukeException
     */
    public String findNearest() {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof Deadline || tasks.get(i) instanceof Event) {
                stringBuilder.append(i + 1).append(".").append(tasks.get(i).toString());
                break;
            }
        }
        return stringBuilder.toString();
    }
}
