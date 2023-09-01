package task;

import dukeutilities.TimeFormatter;

/**
 * The Deadline class represents a task with a specific due date.
 * It provides methods to create a Deadline task and generate file and display strings.
 */
public class Deadline extends Task {
    private String title;
    private TimeFormatter deadline;

    /**
     * Constructs a Deadline object with the specified response string, parsing time and title
     *
     * @param response The user's input representing the deadline task.
     */
    public Deadline(String response) {
        super(false);
        int toTrim = response.indexOf(" ");
        int info = response.indexOf("/");
        this.title = response.substring(toTrim + 1, info - 1);
        this.deadline = new TimeFormatter(response.substring(info + 4));
    }

    /**
     * Generates a string representation of the Deadline task for storage in a file.
     *
     * @return A formatted string representing the Deadline task.
     */
    @Override
    public Boolean compareTitle(String query) {
        return this.title.contains(query);
    }

    @Override
    public String toFileString() {
        if (this.done == true) {
            return "D | 1 | " + this.title + " | " + this.deadline.formatDate();
        }
        return "D | 0 | " + this.title + " | " + this.deadline.formatDate();
    }

    /**
     * Generates a string representation of the Deadline task for display purposes.
     *
     * @return A formatted string representing the Deadline task.
     */
    @Override
    public String toString() {
        String s = String.format("| DUE: %s |", this.deadline.formatDate());
        if (this.done == true) {
            return "[D] " + "[X] " + this.title + " " + s;
        }
        return "[D] " + "[ ] " + this.title + " " + s;
    }

}


