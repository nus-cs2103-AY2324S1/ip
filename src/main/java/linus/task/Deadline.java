package linus.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import linus.exception.LinusException;

public class Deadline extends Task {
    protected String type = "linus.task.Deadline";
    protected LocalDate by = null;

    public Deadline(String description, String by) throws LinusException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new LinusException("☹ OOPS!!! Please specify the deadline in the correct format: yyyy-mm-dd");
        }
    }

    @Override
    public String getTaskTypeIcon() {
        return "[D]";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
