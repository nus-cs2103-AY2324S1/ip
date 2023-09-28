package gudetama.tasks;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event
 */
public class Event extends Task {
    /**
     * End date of event task
     */
    private String end;
    /**
     * Start date of event task
     */
    private String start;

    /**
     * Formatted start date of event task
     */
    private LocalDateTime formattedStart;
    /**
     * Formatted end date of event task
     */
    private LocalDateTime formattedEnd;

    /**
     * Constructor for Event
     * @param description Description of the event task
     * @param start Start date of the event
     * @param end End date of the event
     */
    public Event(String description, String start, String end) {
        super(description.trim());
        this.start = start;
        this.end = end;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                    "dd-MM-yyyy HH:mm:ss");
            formattedStart = LocalDateTime.parse(this.start, formatter);
            formattedEnd = LocalDateTime.parse(this.end, formatter);
        } catch (DateTimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Constructor for Event
     * @param description Description of the event task
     * @param start Start date of the event
     * @param end End date of the event
     * @param done Boolean value that represents if the task is done
     */
    public Event(String description, String start, String end, String done) {
        super(description.trim());
        this.start = start;
        this.end = end;

        if(done.equals("false")){
            this.isDone = false;
        } else {
            this.isDone = true;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                    "dd-MM-yyyy HH:mm:ss");
            formattedStart = LocalDateTime.parse(this.start, formatter);
            formattedEnd = LocalDateTime.parse(this.end, formatter);
        } catch (DateTimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Returns the formatted string representation of the event
     * @return Formatted string representation of the event
     */
    @Override
    public String store() {
        return String.format("E | %s | %s | %s to %s", this.isDone, this.description,
                formattedStart.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")),
                formattedEnd.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
    }

    /**
     * Returns the formatted string representation of the event
     * @return Formatted string representation of the event
     */
    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(),
                formattedStart.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                formattedEnd.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }
}

