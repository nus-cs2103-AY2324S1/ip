import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime deadline;
    public Deadline(String deadlineName, LocalDateTime deadline) {
        super(deadlineName);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline.format(formatter));
    }

    @Override
    public String serialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        return String.format(
                "deadline %s /by %s%s",
                getTaskName(),
                this.deadline.format(formatter),
                (this.isDone() ? " /done" : "")
        );
    }
}
