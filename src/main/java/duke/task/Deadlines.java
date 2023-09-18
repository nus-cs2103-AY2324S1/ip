package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.FormatterDate;

/**
 * Represents a subclass of the task object
 */
public class Deadlines extends Task {
    protected String end;
    protected LocalDate date;

    /**
     * Represents a constructor of deadLine
     * 
     * @param description is the name of the deadline
     * @param originalEnd is the end date of the deadline
     */
    public Deadlines(String description, String originalEnd) {
        super(description);
        this.end = originalEnd;
        this.date = Task.convertDateFromStringtoObj(originalEnd);
        if (date != null) {
            DateTimeFormatter stringFormatter = FormatterDate.BASIC_OUTPUT.formatter;
            this.end = date.format(stringFormatter);
        }
    }

    /**
     * Represents a toString of the Deadline object
     * 
     * @return a string
     */
    @Override
    public String toString() {
        return String.format(
                "| D |%s (by: %s)", super.toString(), end);
    }
}