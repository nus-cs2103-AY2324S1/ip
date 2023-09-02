package MattBot.task;

import MattBot.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
public class Deadline extends Task {
    protected LocalDateTime date;
    public static final DateTimeFormatter DTFORMAT  = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");

    public Deadline(String name, LocalDateTime date) {
        super(name, false);
        this.date = date;
    }
    public Deadline(String name, boolean isDone, LocalDateTime date ) {
        super(name, isDone);
        this.date = date;
    }
    public LocalDateTime getDate() {
        return this.date;
    }

    /*
     * Returns a friendly version of the date.
     *
     * @returns Date in form of May 12 2023 20:00
     */
    public String dateToString() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH':'mm"));
    }

    /*
     * Identifies itself as a Deadline.
     *
     * @returns Character to identify the type of Task.
     */
    public String identifier() {
        return "D";
    }

    /*
     * Returns String form for storage.
     *
     * @returns String for storage format.
     */

    public String toFile() {
        return identifier() + " | " + showStatusAsFile() + " | " + showName() + " | " + getDate().format(DTFORMAT);
    }
    @Override
    public String toString() {
        return String.format("[%s] [%s] %s (by: %s)", this.identifier(), this.showStatus(), this.showName(), dateToString());
    }
}
