package mattbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Implements a Deadline style task.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter DTFORMAT = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
    protected LocalDateTime date;

    /**
     * Constructs a new Deadline Instance that is not done by default.
     */
    public Deadline(String name, LocalDateTime date) {
        super(name, false);
        this.date = date;
    }

    /**
     * Constructs a new Deadline Instance that has no default done status.
     */
    public Deadline(String name, boolean isDone, LocalDateTime date) {
        super(name, isDone);
        this.date = date;
    }

    /**
     * Returns the LocalDateTime for this Deadline.
     *
     * @return LocalDateTime for the Deadline.
     */
    public LocalDateTime getDate() {
        return this.date;
    }

    /**
     * Returns a friendly version of the date.
     *
     * @return Date in form of May 12 2023 20:00
     */
    public String dateToString() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH':'mm"));
    }

    /**
     * Identifies itself as a Deadline.
     *
     * @return Character to identify the type of Task.
     */
    public String identifier() {
        return "D";
    }

    /**
     * Returns String form for storage.
     *
     * @return String for storage format.
     */
    public String toFile() {
        return identifier() + " | " + showStatusAsFile() + " | " + showName() + " | " + getDate().format(DTFORMAT);
    }
    @Override
    public String toString() {
        return String.format("[%s] [%s] %s (by: %s)",
                this.identifier(), this.showStatus(), this.showName(), dateToString());
    }
}
