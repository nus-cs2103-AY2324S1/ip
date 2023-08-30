package duke;
import java.time.LocalDateTime;
/**
 * Encapsulates a deadline with a do-by time/date.
 */
public class Deadline extends Item {
    private LocalDateTime by;

    public Deadline(String input, LocalDateTime by) {
        super(input);
        this.by = by;
    }

    @Override
    public String toString() {
        String rtnVal = "";
        if (super.isCompleted()) {
            rtnVal += "[D][X] ";
        } else {
            rtnVal += "[D][ ] ";
        }
        return rtnVal + super.getName() + " (by: " + DukeEnvironmentConstants.OUTPUT_FORMATTER.format(this.by) + ")";
    }
}
