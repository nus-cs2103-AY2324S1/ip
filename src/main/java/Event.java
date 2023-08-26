import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructs an Event with the specified name, start time, and end time.
     *
     * @param name      The name of the event.
     * @param startDate The start time of the event.
     * @param endDate   The end time of the event.
     */
    private Event(String name, LocalDate startDate, LocalDate endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Parses the command string to create an Event instance.
     *
     * @param input The command string.
     * @return A new Event instance.
     * @throws DukeException If the input format is invalid.
     */
    public static Event createFromCommandString(String input) throws DukeException {
        String[] splitByTo = input.split("/to ", 2);
        if (splitByTo.length < 2) {
            throw new DukeException("Missing '/to' or end date for event.");
        }
        String[] splitByFrom = splitByTo[0].split("/from ", 2);
        if (splitByFrom.length < 2) {
            throw new DukeException("Missing '/from' or start date for event.");
        }
        LocalDate startDate;
        LocalDate endDate;
        try {
            startDate = LocalDate.parse(splitByFrom[1].trim());
            endDate = LocalDate.parse(splitByTo[1].trim());
        } catch (DateTimeParseException dateTimeParseException) {
            throw new DukeException("Event /from or /to dates should be in yyyy-mm-dd format (e.g. 2023-08-25)");
        }

        return new Event(splitByFrom[0], startDate, endDate);
    }

    public static Event fromFileFormat(String[] parts) throws DukeException {
        boolean isDone = "1".equals(parts[1].trim());
        String name = parts[2].trim();
        LocalDate startDate;
        LocalDate endDate;
        try {
            startDate = LocalDate.parse(parts[3].trim());
            endDate = LocalDate.parse(parts[4].trim());
        } catch (DateTimeParseException dateTimeParseException) {
            throw new DukeException("Event /from or /to dates should be in yyyy-mm-dd format (e.g. 2023-08-25)");
        }

        Event event = new Event(name, startDate, endDate);
        if (isDone) {
            event.markAsDone();
        }
        return event;
    }

    @Override
    public String toFileFormat() {
        return "E|" + (isDone ? "1" : "0") + "|" + this.name + "|" + this.startDate + "|" + this.endDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedStart = this.startDate.format(formatter);
        String formattedEnd = this.endDate.format(formatter);
        return "[E]" + super.toString() + " (from: " + formattedStart + " to: " + formattedEnd + ")";
    }
}
