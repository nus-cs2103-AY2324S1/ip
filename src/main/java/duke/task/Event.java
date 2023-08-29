package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import duke.exception.DukeException;
import duke.exception.InvalidTimeException;
public class Event extends Task {
    protected LocalDate starting;
    protected LocalDate ending;

    public Event(String description) throws DukeException {
        super(description.substring(0, description.indexOf("/from") - 1));
        int fromIndex = description.indexOf("/from");
        String tempStarting = description.substring(fromIndex + 6, fromIndex + 16);
        int toIndex = fromIndex + 21;
        this.starting = LocalDate.parse(tempStarting);
        this.ending = LocalDate.parse(description.substring(toIndex));
        if (this.starting.isAfter(this.ending)) {
            throw new InvalidTimeException("The starting time could not pass the ending time");
        }
    }
@Override
    public String getInput() {
        return "E | " + this.getStatusIcon() + " | " + this.description + " | " + this.starting + " | " + this.ending;
    }
    @Override
    public String toString() {
        return "[E] [" + this.getStatusIcon() + "] " + this.description +
                " (from: " + this.starting.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " + this.ending.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
