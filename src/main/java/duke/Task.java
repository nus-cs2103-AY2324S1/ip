package duke;

import java.time.format.DateTimeFormatter;

/**
 * The Task class represents a task with a description, status, and type.
 */
public class Task {
    // inspired by partial solution on website
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    /**
     * Constructs a Task object with the specified description and task type.
     *
     * @param description The description of the task.
     * @param taskType    The type of the task (ToDo, Deadline, or Event).
     */
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Returns an icon representing the status of the task.
     *
     * @return "X" if the task is done, " " (empty) if the task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string in the format "[X] description" if the task is done,
     *         or "[ ] description" if the task is not done.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Checks if the task is done.
     *
     * @return true if the task is done, false if it's not done.
     */
    public boolean isDone() { return this.isDone;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Converts the task to a formatted string for saving to a data file.
     *
     * @return A formatted string representing the task in the format:
     *         "taskType || doneStatus || taskDescription || start || end"
     */
    public String taskToFileString() {
        String taskType = "";
        String doneStatus = this.isDone() ? "1" : "0";
        String taskDescription = this.getDescription();
        String start = "";
        String end = "";

        if (this instanceof ToDo) {
            taskType = "T";
        } else if (this instanceof Deadline) {
            taskType = "D";
            end = ((Deadline) this).date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } else if (this instanceof Event) {
            taskType = "E";
            start = ((Event) this).start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            end = ((Event) this).end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        }

        return taskType + " || " + doneStatus + " || " + taskDescription + " || " + start + " || " + end;
    }
}