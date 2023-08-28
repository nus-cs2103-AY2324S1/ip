package duke;

import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = super.parseStringToTime(by);
    }

    public String toStringForSave() {
        return "D" + " | " + super.toStringForSave() + " | " + super.convertTimeForSave(this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.convertTimeToString(this.by) + ")";
    }
}
