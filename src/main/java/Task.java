/**
 * Task encapsulate a task with a String description and boolean isDone. It supports
 * marking the completion of tasks
 *
 * @author: Low Jun Yu
 * @version: CS2103T AY23/24 Semester 1
 */
public class Task {
    protected String description;
    protected boolean isDone;
    /**
     * Construct a Task with a given description. Completion of the task
     * is false by default
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the completion status of task with "X" meaning task is completed
     * @return "X" if completed " " if still in progress
     */
    public String getStatus() {
        return isDone ? "X" : " ";
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the current task as completed
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the current task as not completed
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

}
