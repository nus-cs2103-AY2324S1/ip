package duke.tasks;

import duke.DatesAndTimesFormatter;

import java.time.LocalDate;

public class Deadline extends Task {
    protected String by;
    protected LocalDate date;

    /**
     * Creates a Deadline object.
     *
     * @param description The description of the Deadline.
     * @param by          The date of the Deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.date = Task.parseDate(by);
        if (date != null) {
            this.by = date.format(DatesAndTimesFormatter.OUTPUT_FORMAT.formatter);
        }
    }

    @Override
    public String toString() {
        return "|D| " + super.toString() + " (by: " + this.by + ")";
    }
}