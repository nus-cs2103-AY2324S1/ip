package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a deadline task with by date in specified format.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a deadline task with by date and description. Transforms the by date to LocalDate.
     * @param description
     * @param by
     * @throws DateTimeParseException if the date format is invalid or the date is before today.
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.taskType = TaskType.DEADLINE;
        Pattern pattern = Pattern.compile("(\\d{4}-\\d{2}-\\d{2})");
        Matcher matcher = pattern.matcher(by);
        if (!matcher.find()) {
            System.out.println("date format is invalid");
            throw new DateTimeParseException("date format is invalid", by, 0);
        } else {
            this.by = LocalDate.parse(matcher.group(1));
            if (this.by.isBefore(LocalDate.now())) {
                throw new DateTimeParseException("date is before today", by, matcher.start());
            }
        }
        assert this.description != null : "description should not be null";
    }

    /**
     * Constructs a deadline task with by date, description and isDone.
     * @param description
     * @param by
     * @param isDone
     */
    public Deadline(String description, String by, boolean isDone) {
        this(description, by);
        this.isDone = isDone;
        assert this.description != null : "description should not be null";
    }

    /**
     * {@inheritDoc}
     * Outputs with by date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * {@inheritDoc}
     * Outputs with by date.
     */
    @Override
    public String toTxt() {
        return super.toTxt() + this.description + " | " + this.by;
    }

    public LocalDate getByDate() {
        return this.by;
    }
}
