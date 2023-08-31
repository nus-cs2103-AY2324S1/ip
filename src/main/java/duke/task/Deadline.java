package duke.task;

import duke.DukeException;
import duke.task.Task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline extends from a Task Class to have an additional attribute by, representing the due date.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a Deadline Object.
     *
     * @param description String containing description of Deadline.
     * @param by String containing deadline of the Deadline in YYYY-MM-DD format.
     * @throws DukeException When the deadline is not in the YYYY-MM-DD format.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            formatDate(by);
        } catch (DateTimeException e) {
            throw new DukeException("Please input your deadline in YYYY-MM-DD format!");
        }
    }

    /**
     * Formats date and parses it into a LocalDate object.
     *
     * @param date String containing date in YYYY-MM-DD format to be parsed.
     */
    public void formatDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.by = LocalDate.parse(date, formatter);
    }

    /**
     * Returns the String representation of the Deadline that is writeable to the data file.
     *
     * @return A String object that has a correct format to be written to data file.
     */
    @Override
    public String save() {
        return "D|" + super.save() + "|" + this.by;
    }

    /**
     * Returns a String representation of the Deadline Object.
     *
     * @return A String representation of the Deadline Object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
