package duke.task;

import java.time.LocalDate;

/**
 * The Task class represents a task template in the Duke application.
 * It includes a description and a boolean flag indicating whether the task is done or not.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    private LocalDate date;
    private TaskPriority priority;

    /**
     * Constructs a Task object with the specified description and done status.
     *
     * @param description The description of the task.
     * @param isDone      The status indicating whether the task is done (true) or not (false).
     * @param date        The date of the task
     * @param priority    The priority of the task - H(High), M(Medium), L(Low)
     */
    public Task(String description, boolean isDone, LocalDate date, TaskPriority priority) {
        this.description = description;
        this.isDone = isDone;
        this.date = date;
        this.priority = priority;
    }

    /**
     * Returns the status icon for the task.
     *
     * @return "X" if the task is done, or a space " " if the task is not done.
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Marks the task as done by setting the isDone flag to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done by setting the isDone flag to false.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Checks if task is Done or not
     *
     * @return True if is done and False otherwise
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns a string representation of the task, including its status icon.
     *
     * @return A string representation of the status of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]";
    }

    /**
     * Returns a string representation of the task for saving to a file.
     *
     * @return A formatted string containing task type, status, and description.
     */
    public String toFileString() {
        return String.format("%s | %d | %s | %s", this.getTaskType(), this.isDone ? 1 : 0, this.priority.toCode(), this.description);
    }

    /**
     * Gets the task type, which should be overridden in subclasses.
     *
     * @return The task type as a string.
     */
    public abstract String getTaskType();

    /**
     * Gets the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the priority of the task.
     *
     * @return The task priority (HIGH, MEDIUM, LOW).
     */
    public TaskPriority getPriority() {
        return this.priority;
    }

    /**
     * Gets the type of the task.
     *
     * @return The task type (TODO, DEADLINE, EVENT).
     */
    public TaskType getType() {
        // Implement this method to return the task type for each task
        if (this instanceof DeadlineTask) {
            return TaskType.DEADLINE;
        } else if (this instanceof TodoTask) {
            return TaskType.TODO;
        } else if (this instanceof EventTask) {
            return TaskType.EVENT;
        } else {
            throw new IllegalArgumentException("Unknown task type");
        }
    }

}
