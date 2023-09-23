package task;

/**
 * Abstract class for all possible tasks
 */
public abstract class Task {
    protected String description;
    protected Boolean isDone;

    /**
     * Public constructor of the class
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Overloaded constructor to create classes with known status
     * @param status done status of task
     * @param description description of the task
     */
    public Task(boolean status, String description) {
        this.description = description;
        this.isDone = status;
    }

    /**
     * Churns out a string 'X' if its complete and ' ' otherwise
     * @return display icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * marks this task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * marks this task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Overriden toString implementation for a generic task
     * @return String representing a generic task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * abstract declaration of a method to format properties of the instance
     * @return String of formatted data of the instance
     */
    public abstract String dataFormat();
}
