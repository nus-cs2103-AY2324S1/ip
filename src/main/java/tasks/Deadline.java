package tasks;

import dogebot.DateTimeHandler;

/**
 * The Deadline class stores the task description and its deadline.
 *
 * @author Kenvyn Kwek
 */
public class Deadline extends Task {
    private DateTimeHandler by;

    /**
     * Initializes a deadline task.
     *
     * @param description Deadline task description.
     * @param by Deadline to complete the task by.
     * @param isDone If the task has already been completed.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = new DateTimeHandler(by);
    }

    /**
     * String representation of a deadline task.
     *
     * @return Deadline task details.
     */
    @Override
    public String toString() {
        return "D | " + super.toString() + " | " + by.toString();
    }
}
