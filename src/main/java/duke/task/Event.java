package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidEventException;

/**
 * Event class
 */
public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    public Event(String name, LocalDate start, LocalDate end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * Creates a new event based on the message
     * 
     * @param message the message to create the new event
     * @return the new event
     * @throws InvalidEventException when the event command message is invalid
     */
    public static Event create(String message) throws InvalidEventException {
        try {
            String name = message.substring(6, message.indexOf("/from "));
            int fromIndex = message.indexOf("/from ");
            int toIndex = message.indexOf(" /to ", fromIndex);
            String fromString = message.substring(fromIndex + 6, toIndex);
            String toString = message.substring(toIndex + 5);
            LocalDate fromDate = LocalDate.parse(fromString);
            LocalDate toDate = LocalDate.parse(toString);
            return new Event(name, fromDate, toDate);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidEventException();
        } catch (DateTimeParseException e) {
            throw new InvalidEventException();
        }
    }

    @Override
    public String toString() {
        String startMonth = start.getMonth().toString().substring(0, 3);
        String startDay = start.toString().split("-")[2];
        String startYear = start.toString().split("-")[0];
        String endMonth = end.getMonth().toString().substring(0, 3);
        String endDay = end.toString().split("-")[2];
        String endYear = end.toString().split("-")[0];
        return "[E]" + super.toString() + " (from: " 
                + startMonth + " " + startDay + " " + startYear + " to: " + endMonth + " " + endDay + " " + endYear + " )";
    }

    @Override
    public String storeInString() {
        return "E | " + (this.getMark() ? "1 | " : "0 | ") + this.getName() + " | " + this.start + " | " + this.end;
    }
}
