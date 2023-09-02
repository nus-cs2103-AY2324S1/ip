import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event instance of a task.
 */
public class Event extends Task{
    private final LocalDate FROM_DATE;
    private String fromTime = "";
    private final LocalDate TO_DATE;
    private String toTime = "";

    /**
     * Constructs a event with a given description. Completion of the task
     * is false by default.
     *
     * @param description The description of the task
     * @param from The start time of the event
     * @param to The end time of the event
     */
    public Event(String description, String from, String to) throws InvalidFormatException {
        super(description);
        String[] fromTemp = from.split("\\s+");
        String[] toTemp = to.split("\\s+");
        try {
            this.FROM_DATE = LocalDate.parse(fromTemp[0]);
            this.TO_DATE = LocalDate.parse(toTemp[0]);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException(e.getMessage());
        }
        if(fromTemp.length == 2) {
            this.fromTime = fromTemp[1];
        }
        if(toTemp.length == 2) {
            this.toTime = toTemp[1];
        }
    }

    /**
     * Returns a formatted string of the status of the task.
     * @return String containing completion status, type, description, from
     * and to of task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + this.FROM_DATE.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " "
                + this.fromTime + " to: " + this.TO_DATE.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + " " + this.toTime + ")";
    }

    /**
     * Returns a string formatted in the way it is to be saved.
     * @return Formatted string to be written into file
     */
    @Override
    public String toSaveFormat() {
        return "E | " + (this.isDone ? 1 : 0) + " | " + this.description + " | "
                + this.FROM_DATE + " " + this.fromTime + "->"
                + this.TO_DATE + " " + this.toTime;
    }
}
