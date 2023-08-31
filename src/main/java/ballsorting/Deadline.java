package ballsorting;
import java.time.LocalDateTime;

public class Deadline extends Task {
    LocalDateTime end;
    public Deadline(String description, LocalDateTime end) {
        super(description);
        this.end = end;
    }
    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.description
                + " (by: " + this.end.format(Ballsorter.outputFormatter) + ")";
    }
    public String toStorageString() {
        int stat = this.isDone ? 1 : 0;
        return "D|" + stat + "|" + this.description + "|" + this.end.format(Ballsorter.inputFormatter);
    }
}
