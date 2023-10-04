package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import duke.parser.TimeParser;

/**
 * Represents a Deadline Task.
 * @author Toh Li Yuan (A0255811H)
 */
public class Deadline extends Task {
    private LocalDateTime date;

    /**
     * Creates a new Deadline Task.
     *
     * @param name the name of the Deadline.
     * @param date the date of the Deadline.
     */
    public Deadline(String name, LocalDateTime date) {
        this.name = name;
        this.date = date;
        this.isDone = false;
        this.type = TaskTypes.DEADLINE;
    }

    /**
     * Creates a new Deadline Task.
     *
     * @param name the name of the Deadline.
     * @param status the completion status of the Deadline.
     * @param date the date of the Deadline.
     */
    public Deadline(String name, boolean status, LocalDateTime date) {
        this.name = name;
        this.date = date;
        this.isDone = status;
        this.type = TaskTypes.DEADLINE;
    }

    @Override
    public String toString() {
        String statusMark = this.isDone ? "[✓]" : "[✕]";
        return String.format("[D]%s %s (by: %s)", statusMark, name, TimeParser.returnTime(date));
    }

    @Override
    public String toSave() {
        return String.format("D%s%s%s%d%s%s", DISCRIMINATOR, name, DISCRIMINATOR,
                Boolean.compare(isDone, false), DISCRIMINATOR, TimeParser.toSaveString(date));
    }

    @Override
    public String getReminder(LocalDate currDate, int days) {
        if (this.isDone) {
            return null;
        }

        if (this.date.getYear() != currDate.getYear()) {
            return null;
        }
        if (currDate.getDayOfYear() + days - this.date.getDayOfYear() >= 0) {
            return this.toString();
        }
        return null;
    }

}
