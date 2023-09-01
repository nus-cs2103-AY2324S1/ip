package ballsorting;
import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime end;

    /**
     * Creates a new instance of Deadline.
     * @param description A brief summary of the Deadline.
     * @param end The due date of the Deadline.
     */
    public Deadline(String description, LocalDateTime end) {
        super(description);
        this.end = end;
    }

    /**
     * Returns a String representation of the Deadline.
     * @return Summary of Deadline.
     */
    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.description
                + " (by: " + this.end.format(Ballsorter.outputFormatter) + ")";
    }

    /**
     * Returns a String representation that is stored in storage.
     * @return Storage summary of Deadline.
     */
    public String toStorageString() {
        int stat = this.isDone ? 1 : 0;
        return "D|" + stat + "|" + this.description + "|" + this.end.format(Ballsorter.inputFormatter);
    }
}
