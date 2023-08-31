package duke;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private String by;
    private LocalDateTime dateTime;

    // Constructor for duke.Deadline
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    // Constructor for duke.Deadline with done status
    public Deadline(String name, String by, boolean isDone) {
        super(name, isDone);
        this.by = by;
    }

    // Constructor for duke.Deadline with date and time
    public Deadline(String name, LocalDateTime dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    // Constructor for duke.Deadline with date and time and done status
    public Deadline(String name, LocalDateTime dateTime, boolean isDone) {
        super(name, isDone);
        this.dateTime = dateTime;
    }

    // Gets string representation of the duke.Deadline
    public String toString() {
        if (by == null) {
            return "[D]" + super.toString() + " (by: " + DateManager.dateTimeToString(dateTime) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    // Gets string representation of the duke.Deadline for hard disk
    public String toStringStorage() {
        String nameField = this.getName();
        String isDoneField = this.isDone() ? "1" : "0";
        String deadlineField = by == null ? DateManager.dateTimeToStringStorage(this.dateTime) : by;
        return "D|" + isDoneField + "|" + nameField + "|" + deadlineField;
    }

    // Gets the date that the duke.Deadline is due by
    public String getBy() {
        return this.by;
    }
}