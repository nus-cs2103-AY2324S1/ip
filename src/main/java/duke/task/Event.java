package duke.task;

import duke.Ui;
import duke.exception.EmptyDescriptionException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }


    /**
     * Initialize a new Event from user input.
     *
     * @param input User input.
     * @return A new Event object.
     * @throws EmptyDescriptionException If user input does not follow the given format.
     */
    public static Event initializeFromInput(String input) throws EmptyDescriptionException {
        try {
            String splitInput = input.split("event")[1];
            String description = splitInput.split("/from")[0].strip();
            String from = splitInput.split("/from")[1].split("/to")[0].strip();
            String to = splitInput.split("/to")[1].strip();
            LocalDateTime fromDateTime = LocalDateTime.parse(from);
            LocalDateTime toDateTime = LocalDateTime.parse(to);
            return new Event(description, fromDateTime, toDateTime);
        } catch (Exception e) {
            throw new EmptyDescriptionException("event", "event project meeting /from 2023-09-09T11:50 /to 2023-09-09T11:55");
        }
    }

    /**
     * Initialize a new Event from file storage.
     *
     * @param input Line from file storage.
     * @return A new Event object.
     */
    public static Event initializeFromStorage(String input) {
        String[] processed = input.split("\\(");
        String taskName = processed[0].trim();
        String from = processed[1].split("from:")[1].split("to:")[0].trim();
        String to = processed[1].split("to:")[1].split("\\)")[0].trim();
        LocalDateTime fromDateTime = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        LocalDateTime toDateTime = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        return new Event(taskName, fromDateTime, toDateTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Ui.outputDateTime(from) + " to: " + Ui.outputDateTime(to) + ")";
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }
}
