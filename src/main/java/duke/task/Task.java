package duke.task;
/*
 * An abstract class that is the supertype of all 
 * tasks that program accepts (Todo, Deadline, Event).
 * 
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /*
     * A method to return the description of the task.
     * 
     * @return description of the task entered by the user.
     */
    public String getDescription() {
        return this.description;
    }

    /*
     * A method that retrieves the marked status of a task.
     * 
     * @return the status of the task based on whether it is marked or not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public boolean isDone() {
        return this.isDone;
    }

    /*
     * A method that marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /*
     * A method that marks task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /*
     * A method that returns the string representation of a task.
     * 
     * @return string representation of the task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public boolean isKey(String keyString) {
        Matcher matcher = Pattern.compile(keyString).matcher(this.getDescription());
        if (!matcher.find()) {
            return false;
        }
        return true;
    }
    public abstract String toBeStored();

}
