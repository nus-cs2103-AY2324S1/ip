package nobita.task;

import nobita.exception.NobitaException;

import java.util.StringJoiner;

/**
 * Class that encapsulates Task.
 *
 * @author Zheng Chenglong
 */
public class Task {

    /** The name of the task */
    private String taskName;

    /** Whether the task is completed or not */
    private boolean isComplete = false;

    /**
     * Constructs a task with a task name.
     *
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Marks the task as complete.
     */
    public void markComplete() {
        this.isComplete = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markIncomplete() {
        this.isComplete = false;
    }

    /**
     * A getter of the status icon if task is completed or not.
     *
     * @return A String representing the status icon.
     */
    private String getStatus() {
        return isComplete ? "[X] " : "[ ] ";
    }

    /**
     * A getter of the task name.
     *
     * @return A String representing the task name.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Return a String representation of Task.
     *
     * @return A String representing of Task.
     */
    @Override
    public String toString() {
        return getStatus() + this.taskName;
    }

    /**
     * Return a String representation of Task that is formatted for file reading and writing.
     *
     * @return A String representation of Task that is formatted for file reading and writing.
     */
    public String toFileFormat() {
        StringJoiner joiner = new StringJoiner(";");
        joiner.add(this.isComplete ? "1" : "0").add(this.taskName);
        return joiner.toString();
    }

    /**
     * Update a field of the task.
     *
     * @param updateField The field of task to be updated.
     * @param updateValue The value of the field to be updated to.
     * @return The updated task.
     * @throws NobitaException If specified update field does not exist.
     */
    public Task setField(String updateField, String updateValue) throws NobitaException {
        if (updateField.toLowerCase().equals("/name")) {
            this.taskName = updateValue;
        } else {
            throw new NobitaException("ToDo task do not have " + updateValue + " field.");
        }
        return this;
    }
}
