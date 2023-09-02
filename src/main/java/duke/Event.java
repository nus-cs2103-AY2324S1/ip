package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents an event
 *
 */
public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    /**
     * Default constructor to store start and end
     *
     * @param name of the task
     * @param start start date
     * @param end end date
     */
    public Event(String name, String start, String end) {
        super(name);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            this.start = LocalDate.parse(start, inputFormatter);
            this.end = LocalDate.parse(end, inputFormatter);
        } catch (Exception e) {
            System.out.println("Invalid date!");
        }
    }

    /**
     * Default display to represent the event object
     *
     * @return gives the default display
     */
    public String display() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        if (done) {
            return "[E][X] " + this.name + " (From: " +
                    start.format(outputFormatter) + " To: " + end.format(outputFormatter) + ")";
        }
        return "[E][] " + this.name + " (From: " +
                start.format(outputFormatter) + " To: " + end.format(outputFormatter) + ")";
    }
}
