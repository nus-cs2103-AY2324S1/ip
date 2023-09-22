package duke;


import java.time.format.DateTimeFormatter;

/**
 * Represents a type of task to be done.
 */

public class TaskType {
    private String task;
    private boolean isCompleted;
    private String dateString;
    private Priority priority;

    /**
     * Constructor for the TaskType class.
     *
     * @param task Description of task
     * @param isCompleted Whether of not the task has been completed
     * @param dateString String containing only datetime-related information pf the task, if any.
     */

    public TaskType(String task, boolean isCompleted, String dateString) {
        this.task = task;
        this.isCompleted = isCompleted;
        this.dateString = dateString;
        this.priority = Priority.NORMAL;
    }

    /**
     * Returns the boolean value of the isCompleted field.
     */

    public boolean getIsCompleted() {
        return isCompleted;
    }

    /**
     * Sets the isCompleted field to false or true.
     *
     * @param x boolean to set the isCompleted field to
     */

    public void setIsCompleted(boolean x) {
        isCompleted = x;
    }

    /**
     * Produces the string representation of a TaskType to be saved in external storage.
     */

    public String saveStringRep() {
        return this.task + "&"
                + String.valueOf(isCompleted) + "&" + this.toShortString()
                + "&" + (dateString == "" ? "null" : dateString)
                + "&" + priority.toString();
    }

    /**
     * Produces a one-letter representation of the TaskType to be shown in lists.
     *
     * @returns one-letter representation of the TaskType
     */

    public String toShortString() {
        return "";
    };

    /**
     * Returns the task description.
     *
     * @returns task description as string
     */

    public String getTaskDesc() {
        return task;
    };

    /**
     * Returns a string of all datetime-related information, suitable to be shown in lists.
     *
     * @returns String of datetime-related information
     */

    public String getFormattedDatetime(DateTimeFormatter formatter) {
        return "";
    }

    /**
     * Sets the priority of a task.
     */
    public void setPriority(int highPriority) {
        if (highPriority == 1) {
            this.priority = Priority.HIGH;
        } else {
            this.priority = Priority.NORMAL;
        }
    }

    /**
     * Returns the priority of a task.
     *
     * @returns priority of a class
     */
    public Priority getPriority() {
        return this.priority;
    }
}
