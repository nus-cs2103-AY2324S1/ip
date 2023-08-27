import java.time.LocalDateTime;

public class Deadline extends Task{
    private LocalDateTime deadline;
    public Deadline(String task, boolean isDone, LocalDateTime deadline) {
        super(task, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toSaveFormat() {
        return "D|" + super.toSaveFormat() + String.format("|%s\n", super.getSaveDateTime(deadline));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format("(by: %s)", super.getDisplayDateTime(deadline));
    }
}
