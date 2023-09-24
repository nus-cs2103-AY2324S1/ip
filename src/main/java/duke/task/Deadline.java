package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

/**
 * The Deadline class represents a task with a specific deadline.
 * It is a subclass of the Task class.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a Deadline task with the specified description and deadline date.
     *
     * @param description The description of the task.
     * @param by The deadline date in string format (e.g., "yyyy-MM-dd").
     * @throws DukeException If there is an issue while parsing the date.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = parseDate(by);
    }

    /**
     * Constructs a Deadline instance with data from storage.
     *
     * @param data Data representation of the task to construct.
     * @return The instance of task constructed.
     */
    public static Deadline constructWithData(String data) throws DukeException {
        int firstSplitIndex = data.indexOf("|");
        int secondSplitIndex = data.indexOf("|", firstSplitIndex + 1);
        boolean isDone = data.substring(0, firstSplitIndex - 1).equals("1");
        String desc = data.substring(firstSplitIndex + 2, secondSplitIndex - 1);
        String by = data.substring(secondSplitIndex + 2);
        Deadline deadline = new Deadline(desc, by);
        if (isDone) {
            deadline.markDone();
        }
        return deadline;
    }

    @Override
    public void updateDetails(String field, String details) throws DukeException {
        if (field.equals("desc")) {
            this.description = details;
        } else if (field.equals("by")) {
            LocalDate newDate = parseDate(details);
            this.by = newDate;
        } else {
            throw new DukeException("OOPS!!! Only /desc /by flags allowed.");
        }
    }

    @Override
    public String toData() {
        String done = String.valueOf(this.isDone ? 1 : 0);
        return "D | " + done + " | " + this.description + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
