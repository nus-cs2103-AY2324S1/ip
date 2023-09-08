package duke;

import com.fasterxml.jackson.annotation.JsonCreator;
import task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a task manager that helps manage the list of tasks for Duke.
 */
public class TaskManager {
    private List<Task> tasks;

    /** Constructs a TaskManager with empty task list. */
    @JsonCreator
    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Lists the tasks in order.
     *
     * @return The string representation of the list of tasks.
     */
    public String listTasks() {
        if (tasks.size() == 0) {
            return "There are no tasks in your list.\n";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); ++i) {
            String item = (i + 1) + "." + tasks.get(i).toString() + "\n";
            sb.append(item);
        }
        return sb.toString();
    }

    /**
     * Adds a task to the end of the list of tasks.
     *
     * @param task The task to be added.
     * @return The string description after adding the task.
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        return "Got it. I've added this task:\n  "
                + task.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.\n";
    }


    /**
     * Marks the i-th task as either done or not done.
     *
     * @param i The index of the task in the list(1-indexed).
     * @param done Whether to mark the task as done or not.
     * @return The String description after marking the task.
     * @throws DukeException If the index is out of bound.
     */
    public String markTask(int i, boolean done) throws DukeException {
        i--;
        if (i < 0 || i >= this.tasks.size()) {
            // invalid index
            throw new DukeException("Please input a valid index for the task to marked/unmarked");
        }

        Task task = this.tasks.get(i);
        task.markTask(done);
        return done
                ? "Nice! I've marked this task as done:\n  " + task.toString() + "\n"
                : "OK, I've marked this task as not done yet:\n  " + task.toString() + "\n";
    }

    /**
     * Deletes the i-th task from the list of tasks.
     *
     * @param i The index of the task to be deleted(1-indexed).
     * @return The string description after deleting the task.
     * @throws DukeException If the index is out of bound.
     */
    public String deleteTask(int i) throws DukeException {
        i--;
        if (i < 0 || i >= this.tasks.size()) {
            // invalid index
            throw new DukeException("Please input a valid index for the task to removed");
        }

        Task task = this.tasks.remove(i);
        return "Noted! I've removed this task:\n  "
                + task.toString() + "\n"
                + "Now you have " + this.tasks.size() + " tasks in the list.\n";

    }

    /**
     * Finds the tasks that has description that matches the pattern.
     *
     * @param pattern The pattern to be matched.
     * @return The string representation of task found.
     */
    public String find(String pattern) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        boolean found = false;
        for (int i = 0; i < tasks.size(); ++i) {
            Task t = tasks.get(i);
            if (t.descriptionContains(pattern)) {
                found = true;
                sb.append(i + 1 + ". ");
                sb.append(t);
                sb.append('\n');
            }
        }

        return found ? sb.toString() : "There is no task that matched.";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Task t : tasks) {
            sb.append(t.toString());
        }
        return sb.toString();
    }
}
