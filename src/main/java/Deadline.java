import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate deadline;

    public Deadline(String description, boolean isDone, LocalDate deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                this.getStatusIcon(),
                this.description,
                this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
        );
    }

    @Override
    public String toFile() {
        return String.format("D | %s | %s | %s",
                    this.isDone ? "1" : "0", this.description, this.deadline);
    }

}
