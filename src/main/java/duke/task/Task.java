package duke.task;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a task in the Duke application.
 * This abstract class serves as the base for different types of tasks that can be created.
 */
public abstract class Task {

    private String task;
    protected boolean isDone;

    /**
     * Constructs a task with the given task description.
     *
     * @param task The description of the task.
     */
    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Constructs a task with the given task description and completion status.
     *
     * @param task The description of the task.
     * @param isDone The completion status of the task.
     */
    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A string containing the task's status icon and description.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + task;
    }

    /**
     * Marks the task as completed.
     *
     * @return The completed task.
     */
    public Task completeTask() {
        this.isDone = true;
        return this;
    }

    /**
     * Marks the task as not completed (undoes completion).
     *
     * @return The task that was marked as not completed.
     */
    public Task undoTask() {
        this.isDone = false;
        return this;
    }

    public String getTask() {
        return this.task;
    }

    /**
     * Writes the task data to a FileWriter.
     *
     * @param fw The FileWriter to write task data to.
     * @throws IOException If there is an issue writing the data.
     */
    public abstract void writeToFile(FileWriter fw) throws IOException;
}
