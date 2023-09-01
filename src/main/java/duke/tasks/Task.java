package duke.tasks;

import java.time.LocalDate;

/**
 * A Task is an object with a name and toggleable status
 */
public abstract class Task {
    protected String name;
    protected boolean isDone;

    public enum Types {
        TODO, DEADLINE, EVENT
    }

    /**
     * Constructs a new Task with description
     * @param name Name of task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Returns icon indicating task's status
     * @return "X" if task is done, " " otherwise
     */
    private String getStatusIcon() {

        return this.isDone? "X" : " ";
    }

    /**
     * Returns task's name
     * @return The name of the task
     */
    public String getName() {

        return this.name;
    }

    /**
     * Marks task as done
     */
    public void markDone() {

        this.isDone = true;
    }

    /**
     * Marks task as not done
     */
    public void markNotDone() {

        this.isDone = false;
    }

    @Override
    public String toString() {

        return String.format("[%s] %s", this.getStatusIcon(), this.getName());
    }

    /**
     * Returns string representation to save to file
     * @return String representation written to file
     */
    public abstract String generateSaveString();

    /**
     * Returns whether the task occurs on a given date
     * @param date Date specified
     */
    public boolean isOccurringOnDate(LocalDate date) {
        return false;
    }

    /**
     * Returns whether the task's name contains the keyword
     * @param keyword Keyword to query for
     * @return True if it contains the keyword, false otherwise
     */
    public boolean hasKeyword(String keyword) {
        return name.contains(keyword);
    }
}
