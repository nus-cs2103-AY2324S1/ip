package seedu.duke;
import java.io.Serializable;

/**
 * Encapsulates the Task class.
 * A Task is an activity that the user must complete.
 */

class Task implements Serializable {
    protected boolean isDone = false;
    protected String name = "";

    /**
     * Creates a Task instance.
     *
     * @param name The name of the task given by the user.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Obtains a String representing the completion status of the task.
     *
     * @return [X] if the task has been completed and [ ] if it has not.
     */
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Obtains a string representing the type of task.
     *
     * @return [T], [D] or [E] for ToDos, Deadlines and Events respectively.
     */
    public String getTaskType() {
        return "";
    }

    /**
     * Obtains the time restriction details in a string format
     *
     * @return A string containing the deadline/duration information for deadlines and events respectively.
     *         Returns an empty string for ToDos.
     */
    public String getTimeInfo() {
        return "";
    }

    /**
     * Marks the task as completed.
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void unMarkTask() {
        this.isDone = false;
    }
}