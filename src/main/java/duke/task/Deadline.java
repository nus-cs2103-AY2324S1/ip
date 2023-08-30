package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task {

    protected String byDescription;
    protected LocalDate by;

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

    public Deadline(String description, String by, boolean isDone) {
        this(description, by);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return this.by != null ? "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " |" + this.byDescription + ")"
                : "[D]" + super.toString() + " (by: " + this.byDescription + ")";
    }

    @Override
    public String toTxt() {
        return this.by != null ? super.toTxt() + this.description + " | " + this.by + this.byDescription : super.toTxt()
                + this.description + " | " + this.byDescription;
    }
}
