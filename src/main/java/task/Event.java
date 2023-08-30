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

    /**
     * Method to check whether the command is event
     * 
     * @param str the command
     * @return whether the command is event
     * @throws InvalidEventException
     */
    public static boolean isEvent(String str) throws InvalidEventException {
        try{
            if(str.split(" ")[0].equals("event")) {
                int fromIndex = str.indexOf("/from ");
                int toIndex = str.indexOf(" /to ", fromIndex);
                if(fromIndex == -1 || toIndex == -1) {
                    throw new InvalidEventException();
                } else {
                    String fromString = str.substring(fromIndex + 6, toIndex);
                    String toString = str.substring(toIndex + 5);
                    LocalDate fromDate = LocalDate.parse(fromString);
                    LocalDate toDate = LocalDate.parse(toString);
                    return true;
                }
            }
            return false;
        } catch(DateTimeParseException e) {
            throw new InvalidEventException();
        }
    }
}
