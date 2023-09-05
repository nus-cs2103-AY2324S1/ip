package seedu.duke.tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.duke.exceptions.InvalidDeadlineException;

/**
 * Taken from the Partial Solution given on https://nus-cs2103-ay2324s1.github.io/website/schedule/week2/project.html
 * A child class of duke.Tasks, it represents  tasks with a deadline.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor of Deadline object which represents a task with a deadline.
     * @param description the task description
     * @param by the deadline of the task
     * @throws InvalidDeadlineException thrown when the deadline is invalid
     */
    public Deadline(String description, String by) throws InvalidDeadlineException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeException e) {
            throw new InvalidDeadlineException("Invalid data");
        }
    }

    /**
     * Constructor of Deadline object which represents a task with a deadline.
     * This constructor can set isDone of the object and is used to load the
     * task list after Lemon is started.
     * @param description the Deadline task's description
     * @param by the deadline of the task
     * @param isDone the completion status of the task
     * @throws InvalidDeadlineException
     */
    public Deadline(String description, String by, boolean isDone) throws InvalidDeadlineException {
        super(description, isDone);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeException e) {
            throw new InvalidDeadlineException("Invalid data");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String addDataFormat() {
        return "D " + super.addDataFormat() + " | " + by;
    }
}
