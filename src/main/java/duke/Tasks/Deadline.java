package duke.Tasks;

import duke.Exceptions.InvalidDeadlineException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    /**
     * Taken from the Partial Solution given on https://nus-cs2103-ay2324s1.github.io/website/schedule/week2/project.html
     * A child class of duke.Tasks to create tasks with a deadline.
     */
    protected LocalDate by;

    public Deadline(String description, String by) throws InvalidDeadlineException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeException e) {
            throw new InvalidDeadlineException("Invalid data");
        }
    }
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