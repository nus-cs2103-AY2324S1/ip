package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.FormatterDate;

/**
 * is a subclass of the task object
 */
public class Deadlines extends Task {
    protected String end;
    protected LocalDate date;

    /**
     * Constructor of deadLine
     * @param description is the name of the deadline
     * @param originalEnd is the end date of the deadline
     */
    public Deadlines(String description, String originalEnd) {
        super(description);
        this.end = originalEnd;
        this.date = Task.convertDatePlease(originalEnd);
        if (date != null) {
            DateTimeFormatter stringFormatter = FormatterDate.basicOutput.formatter;
            this.end = date.format(stringFormatter);
        }
    }

    /**
     * String of the deadLine object
     * @return a string
     */
    @Override
    public String toString() {
        return String.format(
                "| D |%s (by: %s)", super.toString(), end);
    }
}