import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Events extends Task{
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * A constructor the public Events class
     * @param description the description of the event.
     * @param from the beginning time of the event.
     * @param to the ending time of the event.
     */
    public Events(String description, String from, String to) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            this.from = LocalDateTime.parse(from, formatter);
            this.to = LocalDateTime.parse(to, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Please input your date and time in the correct format: yyyy-MM-dd HHmm");
        }

    }

    /**
     * This method converts the value of an Event into a String type.
     * @return the String representation of the event with its type, completion status,
     * beginning time and ending time.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }
}
