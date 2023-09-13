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

    protected String byDescription;
    protected LocalDate by;

    /**
     * Constructs a deadline task with by date and description. Transforms the by date to LocalDate.
     * @param description
     * @param by
     * @throws DateTimeParseException
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.taskType = TaskType.DEADLINE;
        Pattern pattern = Pattern.compile("(\\d{4}-\\d{2}-\\d{2})");
        Matcher matcher = pattern.matcher(by);
        if (matcher.find()) {
            this.by = LocalDate.parse(matcher.group(1));
            this.byDescription = by.replaceFirst(matcher.group(1), "");
        } else {
            this.byDescription = by;
        }
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
    }

    /**
     * {@inheritDoc}
     * Outputs with by date.
     */
    @Override
    public String toString() {
        return this.by != null ? "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " |" + this.byDescription + ")"
                : "[D]" + super.toString() + " (by: " + this.byDescription + ")";
    }

    /**
     * {@inheritDoc}
     * Outputs with by date.
     */
    @Override
    public String toTxt() {
        return this.by != null ? super.toTxt() + this.description + " | " + this.by + this.byDescription : super.toTxt()
                + this.description + " | " + this.byDescription;
    }
}
