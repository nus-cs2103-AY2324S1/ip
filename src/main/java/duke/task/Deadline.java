package duke.task;

import duke.Keyword;
import duke.Storage;
import duke.Time;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String fileFormat() {
        return String.format("D%s%s%s%s",
                Storage.SEPARATOR, super.fileFormat(),
                Storage.SEPARATOR, this.by.format(Time.DATE_TIME_FORMATTER));
    }

    @Override
    public boolean onDate(Keyword key, LocalDate date) {
        LocalDate by = this.by.toLocalDate();
        return key.equals(Keyword.DEADLINE) &&
                (by.isAfter(date) || by.equals(date));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.by.format(Time.DATE_TIME_DISPLAY_FORMATTER));
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deadline) {
            Deadline deadline = (Deadline) o;
            return super.equals(deadline) && this.by.equals(deadline.by);
        }
        return false;
    }
}
