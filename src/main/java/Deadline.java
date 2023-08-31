import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }

    @Override
    public String toTaskListEntry() {
        return String.format("D | %d | %s | %s", this.getIsDone() ? 1 : 0, this.getName(), this.by);
    }
}
