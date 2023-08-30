import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String data() {
        return "D " + super.data() + " /by " + DateTimeManager.dateToStringData(this.deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + DateTimeManager.dateToDisplay(this.deadline) + ")";
    }
}
