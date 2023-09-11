package chatbot.alain;

import java.time.LocalDate;
/**
 * Represents a task with a name and completion status.
 */
public abstract class Task {
    protected String name;
    protected boolean isDone;

    /**
     * Constructs a Task with the given name and sets its completion status to false.
     *
     * @param name The name or description of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Returns a string representing the status icon of the task.
     *
     * @return A string containing "[X]" if the task is done, "[ ]" if the task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Checks if the task description contains a specified keyword.
     *
     * @param keyWord The keyword to search for in the task description.
     * @return True if the keyword is found in the description, false otherwise.
     */
    public Boolean descriptionContain(String keyWord) {
        if (this.name.contains(keyWord)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns a string representation of the task, including its status icon and name.
     *
     * @return A string representation of the task.
     */
    public String toString() {
        return this.getStatusIcon() + " " + this.name;
    }
    public abstract void setTime(LocalDate date, boolean by);
    public abstract LocalDate getDate();
}
