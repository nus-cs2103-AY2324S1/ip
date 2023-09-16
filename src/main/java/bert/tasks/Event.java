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
     * @param description the description of the task that the user inputs
     * @param start the start time that the user inputs
     * @param end the ending time that the user inputs
     */
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Creates an event task that could be done or undone.
     *
     * @param isDone whether the task is done or undone
     * @param description the description of the task that the user inputs
     * @param start the start time that the user inputs
     * @param end the ending time that the user inputs
     */
    public Event(boolean isDone, String description, LocalDate start, LocalDate end) {
        super(isDone, description);
        this.start = start;
        this.end = end;
    }

    /**
     * Creates an event task from the save format.
     *
     * @param formattedTask the save format representation of the event task
     * @return an Event instance
     */
    public static Event createFromSaveFormat(String formattedTask) {
        String[] args = formattedTask.split(" \\| ");
        boolean isDone = args[1].equals("1");
        String[] times = args[3].split("-");
        LocalDate ld1 = LocalDate.parse(times[0], DateTimeFormatter.ofPattern("MMM dd yyyy"));
        LocalDate ld2 = LocalDate.parse(times[1], DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return new Event(isDone, args[2], ld1, ld2);
    }

    @Override
    public String toSaveFormat() {
        return "E | " + super.toSaveFormat() + " | " +
                this.start.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + "-" +
                this.end.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.description +
                " (from: " + this.start.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +
                " to: " + this.end.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
