package Task;
import java.time.LocalDateTime;


public class DeadLine extends Task {
    protected LocalDateTime byDateTime;
    protected String byDescription;

    public DeadLine(String description, String byDescription) {
        super(description);
        this.byDescription = byDescription;
        this.byDateTime = parseDateTime(byDescription);
    }

    @Override
    public String toString() {
        if (byDateTime != null) {
            return getTask() + getStatusIcon() + " " + description + " (by: " + super.printDateTime(this.byDateTime) + ")";
        } else {
            return getTask() + getStatusIcon() + " " + description + " (by: " + byDescription + ")";
        }
    }
}
