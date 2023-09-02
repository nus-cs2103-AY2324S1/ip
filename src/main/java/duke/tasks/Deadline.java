package duke.tasks;

import duke.DatesAndTimesFormatter;

import java.time.LocalDate;

public class Deadline extends Task {
    protected String by;
    protected LocalDate date;

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