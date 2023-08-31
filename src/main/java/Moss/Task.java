package moss;

import java.time.LocalDate;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    public String description;
    public boolean isDone;


    /**
     * Constructs a Moss.Task object with the given description and sets its completion status to false.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets a status icon representing the completion status of the task.
     *
     * @return An "X" if the task is done, otherwise a space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done and prints a confirmation message.
     */
    public void markDone() {
        this.isDone = true;
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
        System.out.println("____________________________________________________________");
    }

    /**
     * Marks the task as not done and prints a confirmation message.
     */
    public void markUndone() {
        this.isDone = false;
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("____________________________________________________________");
        System.out.println(this.toString());
    }

    /**
     * Returns a string representation of the task, including its completion status and description.
     *
     * @return The formatted string.
     */
    public String toString() {
        return getStatusIcon() + " | " + this.description;
    }

    public String toString(String x) {
        return getStatusIcon() + " | " + this.description;
    }
}

