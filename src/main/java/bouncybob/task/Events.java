package bouncybob.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task in the BouncyBob application.
 */
public class Events extends Task {
    private String from;
    private String to;
    private LocalDateTime parsedFrom;
    private LocalDateTime parsedTo;

    /**
     * Constructs a new Event task with the given name, start time, and end time.
     *
     * @param name The name of the task.
     * @param from The start time of the event.
     * @param to   The end time of the event.
     */
    public Events(String name, String from, String to) {
        super(name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.parsedFrom = LocalDateTime.parse(from, formatter);
        this.parsedTo = LocalDateTime.parse(to, formatter);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the symbol representing the event task type.
     *
     * @return "E" for event tasks.
     */
    @Override
    public String getSymbol() {
        return "E";
    }

    /**
     * Returns the description of the event task.
     *
     * @return The description, which includes the name and the formatted start and end times.
     */
    @Override
    public String getDescription() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedFrom = this.parsedFrom.format(formatter);
        String formattedTo = this.parsedTo.format(formatter);
        String description = String.format("%s (from: %s to: %s)", super.getName(), formattedFrom, formattedTo);
        return description;
    }

    /**
     * Converts the event task to its file storage format.
     *
     * @return The string representation of the event task in file storage format.
     */
    @Override
    public String toFileFormat() {
        return super.toFileFormat() + " | " + from + " | " + to;
    }
}
