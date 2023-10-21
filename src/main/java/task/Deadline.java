package task;

import dukeutilities.TimeFormatter;
import exceptions.DukeException;

/**
 * The Deadline class represents a task with a specific due date.
 * It provides methods to create a Deadline task and generate file and display strings.
 */
public class Deadline extends Task {
    private String title;
    private String deadline;

    /**
     * Constructs a Deadline object with the specified response string, parsing time and title
     *
     * @param response The user's input representing the deadline task.
     */
    public Deadline(String response) {
        super(false);
        int info = response.indexOf("/");
        this.title = response.substring(0, info - 1);
        TimeFormatter time = new TimeFormatter(response.substring(info + 4));
        this.deadline = time.formatDate();
    }

    public Deadline(String title, Boolean isDone, String deadline) {
        super(isDone);
        this.title = title;
        this.deadline = deadline;
    }

    /**
     * Edits the title of the Deadline task.
     *
     * @param newTitle The new title for the Deadline task.
     */

    @Override
    public void editTitle(String newTitle) {
        this.title = newTitle;
    }

    /**
     * Edits the deadline of the Deadline task.
     *
     * @param newDeadline The new deadline for the Deadline task.
     */
    @Override
    public void editDeadline(String newDeadline) {
        TimeFormatter newTime = new TimeFormatter(newDeadline);
        this.deadline = newTime.formatDate();
    }

    /**
     * Edits the start time of the Deadline task. Throws an exception as Deadline tasks do not have a start time.
     *
     * @param newStart The new start time for the Deadline task.
     * @throws DukeException if attempting to set a start time for a Deadline task.
     */
    @Override
    public void editStart(String newStart) throws DukeException {
        if (!false) {
            throw new DukeException("This task does not have a start end!");
        }
    }

    /**
     * Edits the end time of the Deadline task. Throws an exception as Deadline tasks do not have an end time.
     *
     * @param newEnd The new end time for the Deadline task.
     * @throws DukeException if attempting to set an end time for a Deadline task.
     */
    @Override
    public void editEnd(String newEnd) throws DukeException {
        if (!false) {
            throw new DukeException("This task does not have an end time!");
        }
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
        if (this.isDone) {
            return "D | 1 | " + this.title + " | " + this.deadline;
        }
        return "D | 0 | " + this.title + " | " + this.deadline;
    }

    /**
     * Generates a string representation of the Deadline task for display purposes.
     *
     * @return A formatted string representing the Deadline task.
     */
    @Override
    public String toString() {
        String s = String.format("| DUE: %s |", this.deadline);
        if (this.isDone) {
            return "[D] " + "[X] " + this.title + " " + s;
        }
        return "[D] " + "[ ] " + this.title + " " + s;
    }

}


