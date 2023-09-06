package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks; // The list of tasks.

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list from the given list of strings.
     * The strings are in the format to be saved in the storage file.
     *
     * @param lines The list of strings representing the tasks in the list.
     * @throws DukeException If there are problems constructing the task list.
     */
    public TaskList(List<String> lines) throws DukeException {
        this.tasks = new ArrayList<>();
        for (String line : lines) {
            String[] inputs = line.split(" \\| ");

            String taskType = inputs[0];
            boolean isDone = inputs[1].equals("1");
            String description = inputs[2];

            switch (taskType) {
            case "T":
                this.add(new Todo(description, isDone));
                break;
            case "D":
                String by = inputs[3];
                this.add(new Deadline(description, by, isDone));
                break;
            case "E":
                String from = inputs[3];
                String to = inputs[4];
                this.add(new Event(description, from, to, isDone));
                break;
            default:
                break;
            }
        }
    }

    /**
     * Returns a list of strings representing the tasks in the list.
     * The strings are in the format to be saved in the storage file.
     *
     * @return A list of strings representing the tasks in the list.
     */
    public List<String> toFileString() {
        List<String> lines = new ArrayList<>();
        for (Task task : this.tasks) {
            lines.add(task.toFileString());
        }
        return lines;
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds the given task to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes the task at the given index from the list.
     *
     * @param index The index of the task to be deleted.
     */
    public void delete(int index) {
        this.tasks.remove(index);
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markAsDone(int index) {
        this.tasks.get(index).markAsDone();
    }

    /**
     * Marks the task at the given index as not done.
     *
     * @param index The index of the task to be marked as not done.
     */
    public void unmarkAsDone(int index) {
        this.tasks.get(index).unmarkAsDone();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns the task at the given index.
     *
     * @param index The index of the task to be returned.
     * @return The task at the given index.
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }
}
