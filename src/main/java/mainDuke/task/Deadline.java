package mainDuke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import mainDuke.exceptions.DukeException;

/**
 * Represents a Task of Deadline type, has a <code>by</code> that indicates when the task should be done
 * by. <code>by</code> is stored in <code>LocalDate</code> format.
 */
public class Deadline extends Task {
    /**
     * Indicates when the task should be done.
     */
    private final LocalDate by;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");

    /**
     * Constructor, returns a <code>Deadline</code> instance.
     * @param done whether the task is done or not
     * @param desc description or name of the task, and when the task must be done by, in
     *            "YYYY-MM-DD" format
     * @throws DukeException error if incorrect format is used for by date and is unable to be parsed
     */
    public Deadline(boolean done, String desc) throws DukeException {
        super(done, desc.substring(9, desc.indexOf("/by")));
        String byString = desc.substring(desc.indexOf("/by") + 4).replace(" ", "");
        try {
            this.by = LocalDate.parse(byString);
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect date format! Use eg.2019-12-02");
        }
    }

    /**
     * Getter for <code>by</code>.
     * @return LocalDate by
     */
    public LocalDate getBy() {
        return this.by;
    }

    /**
     * String representation of Deadline, including task type, if task is done, by date and task name.
     * @return String representation of Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "\n(by: " + this.by.format(formatter) + ")";
    }
}
