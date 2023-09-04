import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private String description;
    private LocalDateTime deadline;

    public Deadline(String name, String description) {
        super(name);
        this.description = description;
    }

    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String detail = description == null
                ? deadline.format(DateTimeFormatter.ofPattern("hh':'mma',' d MMM uuuu',' eee"))
                : description;
        return "[D]" + super.toString() + " (by: " + detail + ")";
    }

}
