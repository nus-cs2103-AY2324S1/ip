package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.FormatterDate;

public class Events extends Task {
    protected String start;
    protected LocalDate startDate;
    protected String end;
    protected LocalDate endDate;

    public Events(String description, String originalStart, String originalEnd) {
        super(description);
        this.start = originalStart;
        this.startDate = Task.convertDatePlease(originalStart);
        this.end = originalEnd;
        this.endDate = Task.convertDatePlease(originalEnd);
        if (startDate != null) {
            DateTimeFormatter stringFormatter = FormatterDate.basicOutput.formatter;
            this.start = startDate.format(stringFormatter);
        }
        if (endDate != null) {
            DateTimeFormatter stringFormatter = FormatterDate.basicOutput.formatter;
            this.end = endDate.format(stringFormatter);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "| E |%s (from: %s to: %s)", super.toString(), start, end);
    }
}
