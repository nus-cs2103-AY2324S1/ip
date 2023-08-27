package duke;

import com.fasterxml.jackson.annotation.JsonCreator;
import task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * CS2103T IP
 * AY 23/24 Semester 1
 *
 * <p> A Task Manager that helps manage the list of tasks </p>
 *
 * @author Koo Yu Cong
 * @version CS2103T AY 23/24 Sem 1
 */
public class TaskManager {
    private List<Task> tasks;

    /** A constructor that creates a TaskManager. */
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
     * Lists the tasks in order
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
     * Marks the i-th task as either done or not done
     *
     * @param i The index of the task in the list(1-indexed)
     * @param done Whether to mark the task as done or not
     * @return The String description after marking the task
     * @throws DukeException if the index is out of bound
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
     * @param i The index of the task to be deleted(1-indexed)
     * @return The string description after deleting the task
     * @throws DukeException DukeException is thrown when the index is out of bound
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
}
