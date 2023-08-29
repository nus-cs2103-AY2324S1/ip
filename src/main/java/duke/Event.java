package duke;
import java.time.LocalDateTime;
/**
 * Encapsulates an event with a start and end time/date.
 */
public class Event extends Item {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Creates a new event
     * @param name Name of event.
     * @param from Start time/date.
     * @param to End time/date.
     */
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String rtnVal = "";
        if (super.isCompleted()) {
            rtnVal += "[E][X] ";
        } else {
            rtnVal += "[E][ ] ";
        }
        return rtnVal + super.getName() + " (from: " + DukeEnvironmentConstants.OUTPUT_FORMATTER.format(this.from) + " to: " + DukeEnvironmentConstants.OUTPUT_FORMATTER.format(this.to) + ")";
    }
}
