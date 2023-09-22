package Jarvis;

/**
 * Abstract class representing a task.
 * <p>
 *     This class serves as the parent for different types of tasks to inherit from and encapsulates
 *     the basic information and operations related to a task.
 * </p>
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected priorityLevels priority;

    protected enum priorityLevels {
        LOW,
        MEDIUM,
        HIGH
    }

    /**
     * Constructs a new Task with the given description
     *
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Maps the priority given to its respective enum value
     * @param priority String representing the priority, either L, M or H
     * @return the corresponding enum value
     */
    public priorityLevels mapPriority(String priority) {
        if (priority.equals("L")) {
            return priorityLevels.LOW;
        } else if (priority.equals("M")) {
            return priorityLevels.MEDIUM;
        } else {
            return priorityLevels.HIGH;
        }
    }

    public void setPriority(String priority) {
        this.priority = mapPriority(priority);
    }

    /**
     * Sets if the task is done or not
     *
     * @param done true if task is done, false otherwise
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Abstract method for string to date conversion
     */
    public void stringToDate() {}

    /**
     * Converts the task to a user-friendly string representation
     *
     * @return A string representation of the task
     */
    public String toString() { // generates the string of marking and task
        String marking = "";
        if (this.isDone) {
            marking = "[X]";
        } else {
            marking = "[ ]";
        }

        String priority = "";
        if (this.priority == priorityLevels.LOW) {
            priority = "[L]";
        } else if (this.priority == priorityLevels.MEDIUM) {
            priority = "[M]";
        } else {
            priority = "[H]";
        }
        return marking + priority + " " + this.description;
    }
}
