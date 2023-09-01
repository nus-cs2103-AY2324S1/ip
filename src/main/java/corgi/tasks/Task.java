package corgi.tasks;

import java.time.format.DateTimeFormatter;

import corgi.storage.Storable;
/**
 * Task class storing description and status.
 */
public abstract class Task implements Storable<Task>{
    protected String desc;
    protected boolean status;
    public static final DateTimeFormatter DATE_INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATE_OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Initializes a new task with its description. The task's initial status is set to not done.
     *
     * @param status The status of the task.
     * @param desc The description of the task.
     */
    public Task(boolean status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    /**
     * Mark task as done.
     */
    public void markAsDone() throws TaskStatusException{
        if(this.status == true) {
            throw new TaskStatusException("The task is already marked as done.");
        }
        this.status = true;
    }

    /**
     * Mark task as not done.
     */
    public void markAsNotDone() throws TaskStatusException{
        if(this.status == false) {
            throw new TaskStatusException("The task is already marked as not done.");
        }
        this.status = false;
    }

    /**
     * Returns an icon representing the status of the task.
     * 
     * @return An icon ("X" for done, " " for not done).
     */
    public String getStatusIcon() {
        return (this.status ? "X" : " ");
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
