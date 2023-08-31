package anto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class represents an event task.
 */
public class Event extends Task {

    /**
     * DateTime representing start of Event.
     */
    protected LocalDateTime from;

    /**
     * DateTime representing end of Event.
     */
    protected LocalDateTime to;

    /**
     * Creates an Event task.
     *
     * @param description Description of the event.
     * @param from String representing start of event.
     * @param to String representing end of event.
     * @throws AntoException Throws Anto Exception if String given is not in
     *                       DateTime format.
     */
    public Event(String description, String from, String to) throws AntoException {
        super(description);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        if (!Parser.isValidDate(from, formatter) || !Parser.isValidDate(to, formatter)) {
            throw new InvalidDateTimeException();
        }
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    /**
     * Overridden toString method to represent Event task.
     *
     * @return String representing Event task.
     */
    @Override
    public String toString() {
        return String.format("[E] [%s] %s (from: %s to: %s)",
                this.getStatusIcon(),
                super.toString(),
                this.from.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")),
                this.to.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
    }
}
