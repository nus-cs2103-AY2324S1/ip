package tasks;

/**
 * Adapted from Partial Solution provided by https://nus-cs2103-ay2324s1.github.io/website/schedule/week2/project.html
 * .
 * The {@code Task} class represents a parent class for creating tasks in a task management application.
 * It includes properties and methods common to all types of tasks.
 * Tasks have two main attributes:
 * - {@code taskDesc}: A string describing the task.
 * - {@code isDone}: A boolean indicating whether the task is marked as done.
 * .
 * This class provides methods to:
 * - Get the task description using {@code getTaskDesc()}.
 * - Get the task's done status using {@code getStatus()}.
 * - Mark the task as done using {@code markedAsDone()}.
 * - Mark the task as not done using {@code markedAsUndone()}.
 * - Get a formatted string representation of the task using {@code toString()}.
 * - Get a formatted data string representation of the task using {@code getData()}.
 */
public class Task {
    protected String taskDesc;
    protected boolean isDone;

    /**
     * Public constructor to create a Event task
     *
     * @param taskDesc Task description
     * @param isDone   Boolean if task is done/marked
     */
    public Task(String taskDesc, boolean isDone) {
        this.taskDesc = taskDesc;
        this.isDone = isDone;
    }

    /**
     * Method to get the task description
     *
     * @return String of task description
     */
    public String getTaskDesc() {
        return this.taskDesc;
    }

    /**
     * Method to get the task done status
     *
     * @return String representation if the task is done
     */
    public String getStatus() {
        return this.isDone ? "x" : " ";
    }

    /**
     * Method to mark the task as done
     *
     * @return Task object
     */
    public Task markedAsDone() {
        this.isDone = true;
        return this;
    }

    /**
     * Method to mark the task as not done
     *
     * @return Task object
     */
    public Task markedAsUndone() {

        this.isDone = false;
        return this;
    }

    /**
     * Method to return the string representation of the task
     *
     * @return Task object
     */
    public String toString() {
        return "[" + this.getStatus() + "] " + this.getTaskDesc();
    }

    /**
     * Method to return the string representation of the task formatted
     *
     * @return String of task
     */
    public String getData() {
        String marked = isDone ? "1" : "0";
        return " | " + marked + " | " + this.taskDesc;
    }
}
