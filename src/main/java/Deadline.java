import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString(){
        String tempString = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
        return "[D] " + super.toString() + " (by: " + tempString +")";
    }
}
