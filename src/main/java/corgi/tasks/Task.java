package corgi.tasks;

import java.time.format.DateTimeFormatter;

import corgi.storage.Storable;
/**
 * Task class storing description and status.
 */
public abstract class Task implements Storable<Task> {
    public static final DateTimeFormatter DATE_INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATE_OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    protected final String desc;
    protected final boolean isDone;

    /**
     * Initializes a new task with its description. The task's initial status is set to not done.
     *
     * @param isDone The status of the task.
     * @param desc The description of the task.
     */
    public Task(boolean isDone, String desc) {
        this.isDone = isDone;
        this.desc = desc;
    }

    /**
     * Marks the task as done.
     *
     * @return A new Task instance with the "done" status set to true, while keeping the original task unchanged.
     * @throws TaskStatusException If an error occurs while marking the task as done.
     */
    public abstract Task markAsDone() throws TaskStatusException;

    /**
     * Marks the task as not done.
     *
     * @return A new Task instance with the "done" status set to false, while keeping the original task unchanged.
     * @throws TaskStatusException If an error occurs while marking the task as not done.
     */
    public abstract Task markAsNotDone() throws TaskStatusException;

    /**
     * Returns an icon representing the status of the task.
     *
     * @return An icon ("X" for done, " " for not done).
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Checks if the description of the task contains a specified keyword.
     *
     * @param keyword The keyword to search for within the task description.
     * @return True if the keyword is found in the description, false otherwise.
     */
    public boolean contains(String keyword) {
        return this.desc.contains(keyword);
    }

    /**
     * Returns a string representation of the task.
     *
     * @return Status icon and description of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.desc;
    }
}
