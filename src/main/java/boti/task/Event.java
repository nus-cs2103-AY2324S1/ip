package boti.task;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import boti.exception.InvalidEventException;

/**
 * Event class
 */
public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    /**
     * Instantiates event
     *
     * @param name the name of the event
     * @param start the starting time of the event
     * @param end the end time of the event
     */
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
        assert message.split(" ")[0].equalsIgnoreCase("create") : "First word of message must be create";
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

    /**
     * Creates new event based on the string stored in the storage
     *
     * @param stored the string stored in the storage
     * @return the event based on the string stored in the storage
     * @throws IOException when the string stored does not belong to event
     */
    public static Event createFromStorage(String stored) throws IOException {
        assert stored.split(" \\| ")[0].equals("E") : "The first part of the string stored is E";
        String[] splitTaskInString = stored.split(" \\| ");
        String mark = splitTaskInString[1];
        String description = splitTaskInString[2];
        String start = splitTaskInString[3];
        String end = splitTaskInString[4];
        Event event = new Event(description, LocalDate.parse(start), LocalDate.parse(end));
        if (mark.equals("1")) {
            event.mark();
        }
        return event;
    }
    @Override
    public String toString() {
        String startMonth = start.getMonth().toString().substring(0, 3);
        String startDay = start.toString().split("-")[2];
        String startYear = start.toString().split("-")[0];
        String endMonth = end.getMonth().toString().substring(0, 3);
        String endDay = end.toString().split("-")[2];
        String endYear = end.toString().split("-")[0];
        return "[E]" + super.toString() + "(from: "
                + startMonth + " " + startDay + " " + startYear
                + " to: " + endMonth + " " + endDay + " " + endYear + ")";
    }

    @Override
    public String storeInString() {
        return "E | " + (this.getMark() ? "1 | " : "0 | ") + this.getName() + " | " + this.start + " | " + this.end;
    }
}
