import java.util.Date;

public class Deadline extends Task {
    // fields
    protected String deadline;

    // toString


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    // Constructor
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }
}
