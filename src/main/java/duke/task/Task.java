package duke.task;

import java.time.LocalDateTime;

/**
 * Task is a class that represents a task that can be added for the chatbot to keep track of.
 * It also manages the list of tasks in a local database.
 */
public abstract class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Turns the task into a string that can be stored in the database.
     *
     * @return The task information in the form of a string.
     */
    public abstract String toDatabaseRepresentation();

    /**
     * Returns the LocalDateTime that is used to chronologically order the task if it is present.
     * @return
     */
    public LocalDateTime getDateTime() {
        return LocalDateTime.MIN;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    protected void markAsUndone() {
        this.isDone = false;
    }

    protected boolean isDone() {
        return isDone;
    }

    protected String getTaskName() {
        return this.taskName;
    }

    private String getStatusIcon() {
        return this.isDone ? "[X]" : "[ ]";
    }

    public String toString() {
        return String.format("%s %s", this.getStatusIcon(), this.getTaskName());
    }
}
