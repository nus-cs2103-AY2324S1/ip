package task;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import exception.InvalidEventException;

/**
 * Event class
 */
public class Event extends Task {
    // Attribute
    private LocalDate start;
    private LocalDate end;

    // Constructor

    /**
     * The constructor of Event class
     * 
     * @param name name of the event
     * @param start the start of the event
     * @param end the end of the event
     */
    public Event(String name, LocalDate start, LocalDate end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    // Method

    /**
     * Creates a new event based on the message
     * 
     * @param message the message to create the new event
     * @return the new event
     * @throws InvalidEventException
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
        } catch(IndexOutOfBoundsException e) {
            throw new InvalidEventException();
        } catch(DateTimeParseException e) {
            throw new InvalidEventException();
        }
    }

    /**
     * Method to return the string representation of event
     * 
     * @return the string representation of event
     */
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

    /**
     * Method to return the string format of the event in the storage
     * 
     * @return the string format of the event in the storage
     */
    @Override
    public String storeInString() {
        return "E | " + (this.getMark() ? "1 | " : "0 | ") + this.getName() + " | " + this.start + " | " + this.end;
    }
}
