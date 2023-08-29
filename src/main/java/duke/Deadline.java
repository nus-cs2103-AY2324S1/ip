package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;

public class Deadline extends Task {

    protected Temporal by;
    protected String deadline;

    public Deadline(String description, String deadline) throws DukeException {
        super(description, "D");
        this.deadline = deadline;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        try {
            if (deadline.contains(" ")) {
                this.by = LocalDateTime.parse(deadline, dateTimeFormatter);
            } else {
                this.by = LocalDate.parse(deadline, dateFormatter);
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Please check that the dates/times you provided are correct!");
        }
    }

    public String getDeadline() {
        return this.deadline;
    }

    public String byString() {
        if (this.by instanceof LocalDate) {
            return ((LocalDate) this.by).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else if (this.by instanceof LocalDateTime) {
            return ((LocalDateTime) this.by).format(DateTimeFormatter.ofPattern("MMM d yyyy, ha"));
        }
        throw new UnsupportedOperationException("Unsupported Temporal type");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byString() + ")";
    }
}