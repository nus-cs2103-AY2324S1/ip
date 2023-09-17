package bert.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that starts at a specific date and ends at a specific date.
 */
public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    /**
     * Creates an event task that is initially undone.
     *
     * @param description The description of the task that the user inputs.
     * @param start The start time that the user inputs.
     * @param end The ending time that the user inputs.
     */
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Creates an event task that could be done or undone.
     *
     * @param isDone Whether the task is done or undone.
     * @param description The description of the task that the user inputs.
     * @param start The start time that the user inputs.
     * @param end The ending time that the user inputs.
     */
    public Event(boolean isDone, String description, LocalDate start, LocalDate end) {
        super(isDone, description);
        this.start = start;
        this.end = end;
    }

    /**
     * Creates an event task from the save format.
     *
     * @param formattedTask The save format representation of the event task.
     * @return An Event instance.
     */
    public static Event createFromSaveFormat(String formattedTask) {
        String[] args = formattedTask.split(" \\| ");
        boolean isDone = args[1].equals("1");
        String[] dates = args[3].split("-");
        LocalDate start = LocalDate.parse(dates[0], DateTimeFormatter.ofPattern("MMM dd yyyy"));
        LocalDate end = LocalDate.parse(dates[1], DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return new Event(isDone, args[2], start, end);
    }

    @Override
    public String toSaveFormat() {
        return "E | " + super.toSaveFormat() + " | "
                + this.start.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + "-"
                + this.end.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.description
                + " (from: " + this.start.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " to: " + this.end.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
