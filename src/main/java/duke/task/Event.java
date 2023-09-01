package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents an event task in the Duke application.
 * It is a subclass of the Task class and inherits its properties and methods.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs a new Event object with the specified description, start, and end date and time.
     *
     * @param description The description of the event task.
     * @param start       The start date and time of the event.
     * @param end         The end date and time of the event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Reads an event task from a file and returns a corresponding Event object.
     *
     * @param components An array of components parsed from a data file line.
     * @return An Event object representing the event task read from the file.
     */
    public static Event readFromFile(String[] components) {
        boolean isDone = components[1].equals("1");
        Event event = new Event(components[2], LocalDateTime.parse(components[3]),
                LocalDateTime.parse(components[4]));
        if (isDone) {
            event.markDone();
        }
        return event;
    }

    /**
     * Returns the task in the format suitable for writing to a data file.
     *
     * @return A string in the file format representing the event task.
     */
    @Override
    public String writeFileFormat() {
        //store as E|1/0|this.start|this.end
        return "E|" + super.writeFileFormat() + "|" + this.start + "|" + this.end;
    }

    /**
     * Returns a string representation of the event task, including its status icon, description,
     * and the start and end date and time.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E] "
                + super.toString()
                + "(from: "
                + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + " to: "
                + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + ")";
    }
}
