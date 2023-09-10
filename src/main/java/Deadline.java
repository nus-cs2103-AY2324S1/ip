import java.util.Date;

public class Deadline extends Task {
    // fields
    protected String deadline;

    // toString


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    @Override
    public String saveToString() {
        return "deadline," + super.saveToString() + "," + deadline;
    }

    // Constructor
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }
    public Deadline(String description, int mark, String deadline) {
        super(description, mark != 0);
        this.deadline = deadline;
    }
}
