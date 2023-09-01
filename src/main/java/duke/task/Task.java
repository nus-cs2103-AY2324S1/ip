package duke.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An abstract class that is the supertype of all 
 * tasks that program accepts (Todo, Deadline, Event).
 */
public abstract class Task {
    protected String description;
    protected boolean isDone = false;

    /**
     * Constructor for the Task object
     * @param description Description of Task object
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * A method to return the description of the task.
     * @return description of the task entered by the user.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * A method that retrieves the marked status of a task.
     * @return the status of the task based on whether it is marked or not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * A method that marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * A method that marks task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * A method that returns the string representation of a task
     * @return string representation of the task
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * A method that returns whether the Task object has a description such that
     * inputted keyString is a substring of the description
     * @param keyString string to check against task description
     */
    public boolean isKey(String keyString) {
        Matcher matcher = Pattern.compile(keyString).matcher(this.getDescription());
        if (!matcher.find()) {
            return false;
        }
        return true;
    }

    /**
     * An abstract method that returns string representation of Task object to be stored
     * @return String to be stored in the database
     */
    public abstract String toBeStored();

}
