package duke.task;

public class Task {
    private String task;
    private boolean done;

    /**
     * The Task class represents a basic task with a description and completion status.
     *
     * @param task
     * @param done
     */
    public Task(String task, boolean done) {
        this.task = task;
        this.done = done;
    }

    /**
     * Returns a string representation of the Task, including the completion status.
     *
     * @return A formatted string representing the task and its completion status.
     */
    public String toString() {
        String message;
        if (this.done) {
            message = "[X] " + this.task;
        } else {
            message = "[ ] " + this.task;
        }
        return message;
    }

    /**
     * Returns a string representation of the Task for saving to a file.
     *
     * @return The formatted string suitable for saving to file.
     */
    public String getTaskFileString() {
        return (done ? "1" : "0") + " , " + this.task;
    }

    /**
     * Marks the task as completed.
     *
     * @return The updated completion status.
     */
    public boolean markTask() {
        this.done = true;
        return this.done;
    }

    /**
     * Marks the task as not completed.
     *
     * @return The updated completion status.
     */
    public boolean unmarkTask() {
        this.done = false;
        return this.done;
    }

}
