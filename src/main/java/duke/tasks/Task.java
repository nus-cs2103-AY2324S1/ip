package duke.tasks;

import java.time.format.DateTimeFormatter;

/**
 * Adapted from Partial Solution given in Level-3 of
 * https://nus-cs2103-ay2324s1.github.io/website/schedule/week2/project.html
 * 
 * Parent tasks class to create tasks objects
 * Tasks have a desciption of what the task is and 
 * a boolean to indicate if it is done
 */

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected DateTimeFormatter DATE_TIME_OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String toFileString() {
        return "";
    }
}