import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;
    public Event(String description, LocalDate from, LocalDate to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * returns string representation of event task.
     * @return string representation.
     */
    @Override
    public String toString() {
        String formattedFrom = this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedTo = this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (from: " + formattedFrom + " to: " + formattedTo+ ")";
    }

    @Override
    public String formatTask() {
        return "E" + super.formatTask() + " | " + from + " | " + to;
    }


}
