import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Class constructor specifying the description of the event task.
     * @param description the string description of the event
     * @param from the string description of time to begin the event
     * @param to the string description of time to end the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Returns a string description which contains the starting and ending time of the event.
     * @return a string representation of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy"))+ ")";
    }

    public String saveTask() {
        return "E|" + (this.isDone ? "X|" : " |") + this.description + "|" + this.from + "|" + this.to;
    }

}